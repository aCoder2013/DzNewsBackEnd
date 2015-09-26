package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import song.NewsServiceEndApplication;
import song.repository.NewsDetailRepository;
import song.repository.NewsItemRepository;
import song.spider.CollectRegularNews;

/**
 * Created by Song on 2015/9/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsServiceEndApplication.class)
public class ColllectRegularNewsTest {


    @Autowired
    private NewsItemRepository newsItemRepository ;
    @Autowired
    private NewsDetailRepository newsDetailRepository;




    @Test
    public void  test(){
        CollectRegularNews news = new CollectRegularNews(newsItemRepository, newsDetailRepository);
        news.collect("http://www.cnbeta.com/articles/433807.htm");
    }

}
