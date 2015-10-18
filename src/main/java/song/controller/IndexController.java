package song.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import song.model.NewsItem;
import song.repository.NewsItemRepository;


/**
 * Created by Song on 2015/6/12.
 */
@Controller
@SessionAttributes("newsList")
public class IndexController {


    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private NewsItemRepository newsItemRepository;

    /*
        跳转到主页
     */
    @RequestMapping("/")
    public String home(Model model, Pageable pageable) {
        Page<NewsItem> newsItemList = newsItemRepository.findAllByOrderByPubTime(pageable);
        model.addAttribute("newsList", newsItemList.getContent());
//        model.addAttribute("pagenum", newsItemList.hasNext() ? (newsItemList.nextPageable().getPageNumber()>0?newsItemList.nextPageable().getPageNumber():0) : 0);
        model.addAttribute("pagenum",pageable.getPageNumber());
        return "home";
    }



}
