package song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import song.exception.NewsNotFoundException;
import song.model.Comment;
import song.model.NewsDetail;
import song.model.NewsItem;
import song.repository.NewsDetailRepository;
import song.service.NewsDetailService;
import song.service.NewsItemService;

import java.util.List;

/**
 * Created by Song on 2015/10/20.
 */
@RestController
@RequestMapping("api")
public class APIController {

    @Autowired
    private NewsItemService itemService;

    @Autowired
    private NewsDetailService detailService;




    /**
     * API:获取单页新闻
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/news")
    public List<NewsItem> showNewsPage(Pageable pageable){
        return itemService.findAll(pageable);
    }

    /**
     * API：根据指定NewsItem ID
     *      获取新闻详情
     */
    @RequestMapping(value = "/news/detail/{id}")
    public NewsDetail showNewsDetail(@PathVariable("id")Long id){
        NewsDetail detail = detailService.findByNewsItemId(id);
        return detail;
    }

    /**
     * 获取指定ID新闻
     * @param id
     * @return
     */
    @RequestMapping(value = "/news/{id}")
    public NewsItem showNewsItem(@PathVariable Long id){
        NewsItem item  = itemService.get(id);
        return item;
    }

    @RequestMapping(value = "/detail/{id}/comment/new",method = RequestMethod.POST)
    public Comment addComment(@PathVariable("id") Long detailId,Comment comment){
            System.out.println(comment);
            return detailService.addComment(detailId,comment);
    }

    @RequestMapping(value = "/detail/{id}/comment")
    public List<Comment> showAllComments(@PathVariable("id") Long detailId){
        return detailService.getAllComments(detailId);
    }

}
