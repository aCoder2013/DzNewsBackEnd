package song.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.core.exception.NewsNotFoundException;
import song.core.model.Comment;
import song.core.model.NewsDetail;
import song.core.repository.CommentRepository;
import song.core.repository.NewsDetailRepository;
import javax.annotation.PostConstruct;
import javax.cache.Cache;
import javax.cache.CacheManager;
import java.util.Date;
import java.util.List;

/**
 * Created by Song on 2015/10/22.
 */
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = NewsDetailService.CACHE_DETAILS)
public class NewsDetailService extends BaseService<NewsDetail,Long> {

    public static final String CACHE_DETAILS = "details";
    @Autowired
    private NewsDetailRepository detailRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    private void init(){
        //必须调用
        setCrudRepository(detailRepository);
    }

    @Override
    @CachePut(key = "#entity.getId()")
    @Transactional(readOnly = false)
    public NewsDetail  save(NewsDetail entity) {
        return detailRepository.save(entity);
    }


    @Transactional(readOnly = false)
    public List<NewsDetail>  save(List<NewsDetail> entities) {
        List<NewsDetail> detailList =  detailRepository.save(entities);
        putInCache(detailList);
        return detailList;
    }


    @Override
    @Cacheable(key="#id")
    public NewsDetail findOne(Long id) {
        NewsDetail detail = detailRepository.findOne(id);
        if(detail==null) throw new NewsNotFoundException();
        return detail;
    }

    /**
     * 将List<>放入缓存中
     * @param entities
     */
    private void putInCache(List<NewsDetail> entities){
        Cache cache  = cacheManager.getCache(CACHE_DETAILS);
        for(NewsDetail detail : entities){
            cache.put(detail.getId(),detail);
        }
    }


    @Transactional(readOnly = false)
    public Comment  addComment(Long detailId ,Comment comment){
        NewsDetail detail =  detailRepository.findOne(detailId);
        if(detail==null) throw  new NewsNotFoundException();
        comment.setPub_time(new Date());
        comment.setDetail(detail);
        Comment co  = commentRepository.save(comment);
        detail.getComments().add(co);
        detailRepository.save(detail);
        return co;
    }

    public List<Comment> getAllComments(Long detailId){
        NewsDetail detail =  detailRepository.findOne(detailId);
        if(detail==null) throw  new NewsNotFoundException();
        return detail.getComments();
    }
}
