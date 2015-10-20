package song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import song.exception.NewsNotFoundException;
import song.model.NewsDetail;
import song.model.NewsItem;
import song.repository.NewsDetailRepository;
import song.repository.NewsItemRepository;

import java.util.List;

/**
 * Created by Song on 2015/10/20.
 */
@RestController
@RequestMapping("api")
public class APIController {

    @Autowired
    private NewsItemRepository newsItemRepository;

    @Autowired
    private NewsDetailRepository newsDetailRepository;




    /**
     * API:获取单页新闻
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/news")
    public List<NewsItem> showNewsPage(Pageable pageable){
        return newsItemRepository.findAll(pageable).getContent();
    }

    /**
     * API：根据指定NewsItem ID
     *      获取新闻详情
     */
    @RequestMapping(value = "/news/detail/{id}")
    public NewsDetail showNewsDetail(@PathVariable("id")Long id){
        NewsDetail detail = newsItemRepository.findOne(id).getNewsDetail();
        if(detail == null) throw new NewsNotFoundException("News Detail with " +id+" doesn't find");
        return detail;
    }

    /**
     * 获取指定ID新闻
     * @param id
     * @return
     */
    @RequestMapping(value = "/news/{id}")
    public NewsItem showNewsItem(@PathVariable Long id){
        NewsItem item  = newsItemRepository.findOne(id);
        if(item==null) throw new NewsNotFoundException("News with "+ id +" doesn't exit .");
        return item;
    }


}
