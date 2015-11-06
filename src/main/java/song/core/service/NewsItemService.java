package song.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.core.exception.NewsNotFoundException;
import song.core.model.NewsItem;
import song.core.repository.NewsItemRepository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Song on 2015/10/21.
 */
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "items")
public class NewsItemService extends BaseService<NewsItem,Long> {

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
        return item;
    }

    /**
     * 同时抓取NewsDetail
     * @param id
     * @return
     */
    @Cacheable(key = "#id")
    public NewsItem getWithNewsDetail(Long id){
        NewsItem item = itemRepository.findByIdFetchNewsDetailEarly(id);
        if(item==null) throw new NewsNotFoundException();
        return item;
    }


    @Override
    @Transactional(readOnly = false)
    @CacheEvict(value = "items",key ="0")
    public NewsItem save(NewsItem entity) {
        return super.save(entity);
    }


    /**
     * 当爬虫抓取最新新闻时,清除当前第一页的缓存，
     * 并将itemList放入缓存中
     * @param itemList
     * @return
     */
    @Transactional(readOnly = false)
    @Caching(evict = {@CacheEvict(value = "items",key ="0")},
            put = {@CachePut(value = "items",key = "0")})
    public List<NewsItem> save(List<NewsItem> itemList) {
        return itemRepository.save(itemList);
    }

    @Override
    @Transactional(readOnly = false)
    @CacheEvict(value = "items",allEntries = true)
    public void delete(Long id ) {
        super.delete(id);
    }

    /**
     *  根据时间排序
     *  将页数作为Cache的Key
     * @param pageable
     * @return
     */
    @Cacheable(value = "items",key = "#pageable.getPageNumber()")
    public List<NewsItem>  findRecentNews(Pageable pageable){
        List<NewsItem> itemList = itemRepository.findAllByOrderByPubTime(pageable).getContent();
        if(itemList ==null)throw new NewsNotFoundException();
        return itemList;
    }

    public List<NewsItem> findAll(){
        List<NewsItem> itemList = itemRepository.findAll();
        if(itemList==null) throw new NewsNotFoundException();
        return itemList;
    }
}
