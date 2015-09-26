package song.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import song.model.NewsItem;
import song.model.User;
import song.repository.NewsItemRepository;
import song.repository.UserRepository;
import song.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 控制器:用户
 * Created by Song on 2015/6/12.
 */
@Controller
@SessionAttributes(value = {"user","newsList"})
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsItemRepository newsItemRepository;

    /**
     * 登录
     * 未进行密码加密
     * @param email
     * @param password
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,Model model,HttpServletRequest request) {
        if (!StringUtils.isEmpty(email, password)) {
            User user = userRepository.findByEmailAndPassword(email, password);
            if (user==null) {
                model.addAttribute("error","用户名或密码错误!");
                return "index";
            }
            List<NewsItem> newsList = newsItemRepository.findAll();
            model.addAttribute("user",user);
            model.addAttribute("newsList",newsList);
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("newsList",newsList);
        }
        return "news_admin";
    }
}
