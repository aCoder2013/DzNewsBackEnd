package song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.exception.NewsNotFoundException;
import song.model.NewsItem;
import song.repository.NewsItemRepository;

import javax.annotation.PostConstruct;

/**
 * Created by Song on 2015/10/21.
 */
@Service
@Transactional
@Lazy
public class NewsItemService extends BaseService<NewsItem,Long> {

    @Autowired
    private NewsItemRepository repository ;

    @PostConstruct
    private void init(){
        //必须调用
        setCrudRepository(repository);
    }
    @Transactional(readOnly = true)
    public Page<NewsItem> findRecentNews(Pageable pageable){
        Page<NewsItem> items = repository.findAllByOrderByPubTime(pageable);
        if(items==null)throw new NewsNotFoundException();
        return items;
    }
}
