package song.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import song.model.NewsDetail;
import song.model.NewsItem;
import song.service.NewsItemService;



/**
 * Created by Song on 2015/6/13.
 */
@Controller
@RequestMapping("/news")
@SessionAttributes("newsList")
public class NewsController {

    private Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsItemService itemService;





    /**
        展示新闻细节页面
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showNewsDetail(@PathVariable("id") Long id, Model model) {
        NewsItem item = itemService.get(id);
        NewsDetail detail = item.getNewsDetail();
        model.addAttribute("news", detail);
        return "news_detail";
    }

}