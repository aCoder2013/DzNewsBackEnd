package song.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import song.exception.NewsNotFoundException;
import song.model.NewsDetail;
import song.model.NewsItem;
import song.repository.NewsDetailRepository;
import song.repository.NewsItemRepository;
import song.service.NewsItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by Song on 2015/6/13.
 */
@Controller
@RequestMapping("/news")
@SessionAttributes("newsList")
public class NewsController {

    private Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsItemRepository newsItemRepository;
    @Autowired
    private NewsDetailRepository newsDetailRepository;


    /**
     跳转到添加新闻页面
     */
    @RequestMapping(value = "/addNewsPage", method = RequestMethod.GET)

    public String toAddNewsPage() {
        return "add_news";
    }



    /*
        展示新闻细节页面
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showNewsDetail(@PathVariable("id") NewsItem news, Model model, HttpServletResponse response) {
        if (news == null) throw new NewsNotFoundException("News : " + news.getId() + " doesn't exit .");
        NewsDetail detail = news.getNewsDetail();
        model.addAttribute("news", detail);
        news.setBeenRead(news.getBeenRead() + 1);
        newsItemRepository.save(news);//更新数据
        return "news_detail";
    }

    /**
     * 删除新闻
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteNews(@PathVariable("id") long id, Model model, HttpServletRequest request) {
        newsItemRepository.delete(id);
        List<NewsItem> newsList = newsItemRepository.findAll();
        model.addAttribute("newsList", newsList);
        request.getSession().setAttribute("newsList", newsList);
        return "redirect:/admin/show";
    }

    /*
        刷新模型
        跳转到后台首页
     */
    @RequestMapping("")
    public String admin(Model model, HttpServletRequest request) {
        request.removeAttribute("newsList");
        List<NewsItem> newsList = newsItemRepository.findAll();
        model.addAttribute("newsList", newsList);
        request.getSession().setAttribute("newsList", newsList);
        return "news_admin";
    }

    /*
        修改文章
     */
    @RequestMapping("/updateNews")
    public String updateNews(@RequestParam("id") NewsItem news, Model model) {
        model.addAttribute("news", news);
        model.addAttribute("content", news.getNewsDetail().getContent());
        return "add_news";
    }

    

   /* @RequestMapping(value = "/abs")
    public String referesh(){
        List<NewsItem> itemList = newsItemRepository.findAll();
        for(NewsItem item : itemList){
            item.getNewsDetail().setAuth(item.getAuth());
            item.getNewsDetail().setPubTime(item.getPubTime());
            newsDetailRepository.save(item.getNewsDetail());
        }
        return "/";
    }*/
}