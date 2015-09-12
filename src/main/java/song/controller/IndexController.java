package song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import song.model.NewsItem;
import song.repository.NewsItemRepository;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Song on 2015/6/12.
 */
@Controller
@SessionAttributes("newsList")
public class IndexController {
    @Autowired
    private NewsItemRepository newsItemRepository;

    /*
        跳转到主页
     */
    @RequestMapping("/")
    public String home(Model model,Pageable pageable){
        Page<NewsItem> newsItemPage = newsItemRepository.findAll(pageable);
        model.addAttribute("newsList",newsItemPage.getContent());
        model.addAttribute("pagenum",newsItemPage.hasNext()?newsItemPage.nextPageable().getPageNumber():0);
        return "home";
    }




    /*
        跳转到后台主页
     */
    @RequestMapping("/admin")
    public String index(){
        return  "index";
    }

}
