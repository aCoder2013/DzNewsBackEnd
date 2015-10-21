package test;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import song.NewsServiceEndApplication;
import song.model.NewsDetail;
import song.model.NewsItem;
import song.repository.NewsDetailRepository;
import song.repository.NewsItemRepository;



/**
 * Created by Song on 2015/9/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsServiceEndApplication.class)
public class NewsItemResTest {


    @Autowired
    private NewsItemRepository repository;

    @Autowired
    private NewsDetailRepository detailRepository;

    @Test
    public  void test(){
        Page<NewsItem> list = repository.findAllByOrderByPubTime(new PageRequest(1, 20));
    }


    @Test
    public void testFindNewsDetail(){

        NewsDetail detail = detailRepository.findNewsDetailByNewsItemId(406L);
        org.springframework.util.Assert.notNull(detail);
    }


}
