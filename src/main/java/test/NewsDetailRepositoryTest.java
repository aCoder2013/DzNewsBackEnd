package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import song.NewsServiceEndApplication;
import song.model.NewsDetail;
import song.repository.NewsDetailRepository;

/**
 * Created by Song on 2015/10/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsServiceEndApplication.class)
//@ContextConfiguration(classes = NewsServiceEndApplication.class)
public class NewsDetailRepositoryTest {


    @Autowired
    private NewsDetailRepository detailRepository;

    @Test
    public void testAddNewsDetail(){
        NewsDetail newsDetail = new NewsDetail();
        newsDetail.setAuth("Song");
        newsDetail.setComNumber(122);
        newsDetail.setContent("<p>Hello</p>");
        newsDetail.setTitle("Hello");
        newsDetail = detailRepository.save(newsDetail);
        System.out.println(newsDetail.getId());
        System.out.println(newsDetail.getPubTime());
    }
}
