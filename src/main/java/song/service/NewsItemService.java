package song.service;

import org.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.exception.NewsNotFoundException;
import song.model.NewsItem;
import song.repository.NewsItemRepository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Song on 2015/10/21.
 */
@Service
@Transactional
@CacheConfig(cacheNames = "items")
public class NewsItemService extends BaseService<NewsItem,Long> {

    static int i =0;
    @Autowired
    private NewsItemRepository itemRepository ;
    private Logger logger = LoggerFactory.getLogger(NewsItemService.class);


    @PostConstruct
    private void init(){
        //必须调用
        setCrudRepository(itemRepository);
    }
    @Cacheable(key = "#id")
    public NewsItem get(Long id ){
        NewsItem item = itemRepository.findOne(id);
        if(item==null)throw new NewsNotFoundException("News with "+id +" not found ");
        item.setBeenRead(item.getBeenRead()+1);
        itemRepository.save(item);
        logger.warn("===" +i+"====");
        i++;
        return item;
    }




    @Transactional(readOnly = true)
    @Cacheable
    public List<NewsItem>  findRecentNews(Pageable pageable){
        List<NewsItem> itemList = itemRepository.findAllByOrderByPubTime(pageable).getContent();
        if(itemList ==null)throw new NewsNotFoundException();
        return itemList;
    }

    @Cacheable
    public List<NewsItem> findAll(){
        List<NewsItem> itemList = itemRepository.findAll();
        if(itemList==null) throw new NewsNotFoundException();
        return itemList;
    }

    /**
     *  根据时间排序
     * @param pageable
     * @return
     */
    @Cacheable
    public List<NewsItem> findAll(Pageable pageable) {
        List<NewsItem> itemList = itemRepository.findAllByOrderByPubTime(pageable).getContent();
        if(itemList==null) throw new NewsNotFoundException();
        return itemList;
    }
}
