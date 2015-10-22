package song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.exception.NewsNotFoundException;
import song.model.NewsDetail;
import song.repository.NewsDetailRepository;

import javax.annotation.PostConstruct;

/**
 * Created by Song on 2015/10/22.
 */
@Service
@Transactional
@CacheConfig(cacheNames = "details")
public class NewsDetailService extends BaseService<NewsDetail,Long> {

    @Autowired
    private NewsDetailRepository detailRepository;

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
}
