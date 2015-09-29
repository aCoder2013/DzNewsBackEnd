package song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import song.model.Admin;
import song.model.NewsItem;
import song.repository.AdminRepository;
import song.repository.NewsItemRepository;
import song.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * Created by Song on 2015/9/28.
 */
@Controller
@RequestMapping("/admin")
@SessionAttributes(value = {"admin","newsList"})
public class AdminController {


    /**
     * 跳转到后台主页
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @Autowired
    private AdminRepository adminRepository;
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
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password
            ,Model model,HttpServletRequest request) {
        if (!StringUtils.isEmpty(email, password)) {
            Admin admin = adminRepository.findByEmailAndPassword(email, password);
            if (admin==null) {
                model.addAttribute("error","用户名或密码错误!");
                return "index";
            }
            List<NewsItem> newsList = newsItemRepository.findAll();
            model.addAttribute("admin",admin);
            model.addAttribute("newsList",newsList);
            request.getSession().setAttribute("user",admin);
            request.getSession().setAttribute("newsList",newsList);
        }
        return "redirect:/admin/show";
    }

    /**
     * 展示新闻相关操作
     * @return
     */
    @RequestMapping(value = "/show")
    public String show(){
        return "news_admin";
    }
}
