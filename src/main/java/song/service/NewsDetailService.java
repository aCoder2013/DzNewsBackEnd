package song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.exception.NewsNotFoundException;
import song.model.Comment;
import song.model.NewsDetail;
import song.repository.CommentRepository;
import song.repository.NewsDetailRepository;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * Created by Song on 2015/10/22.
 */
@Service
@Transactional
@CacheConfig(cacheNames = "details")
public class NewsDetailService extends BaseService<NewsDetail,Long> {

    @Autowired
    private NewsDetailRepository detailRepository;

    @Autowired
    private CommentRepository commentRepository;
    @PostConstruct
    private void init(){
        //必须调用
        setCrudRepository(detailRepository);
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#id")
    public NewsDetail findByNewsItemId(Long id){
        NewsDetail detail = detailRepository.findNewsDetailByNewsItemId(id);
        if (null == detail) throw new NewsNotFoundException();
        return detail;
    }

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
