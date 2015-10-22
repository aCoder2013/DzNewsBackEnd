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
import java.util.List;

/**
 * Created by Song on 2015/10/21.
 */
@Service
@Transactional
public class NewsItemService extends BaseService<NewsItem,Long> {

    @Autowired
    private NewsItemRepository itemRepository ;

    @PostConstruct
    private void init(){
        //必须调用
        setCrudRepository(itemRepository);
    }

    public NewsItem get(Long id ){
        NewsItem item = itemRepository.findOne(id);
        if(item==null)throw new NewsNotFoundException("News with "+id +" not found ");
        item.setBeenRead(item.getBeenRead()+1);
        itemRepository.save(item);
        return item;
    }


    @Transactional(readOnly = true)
    public Page<NewsItem> findRecentNews(Pageable pageable){
        Page<NewsItem> items = itemRepository.findAllByOrderByPubTime(pageable);
        if(items==null)throw new NewsNotFoundException();
        return items;
    }

    public List<NewsItem> findAll(Pageable pageable) {
        List<NewsItem> itemList = itemRepository.findAll(pageable).getContent();
        if(itemList==null) throw new NewsNotFoundException();
        return itemList;
    }


}
