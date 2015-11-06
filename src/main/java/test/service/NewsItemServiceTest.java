package test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import song.NewsServiceEndApplication;
import song.core.service.NewsItemService;

/**
 * Created by Song on 2015/11/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(NewsServiceEndApplication.class)
public class NewsItemServiceTest {

    @Autowired
    private NewsItemService itemService;


    @Test
    public void  test(){
        itemService.findRecentNews(new PageRequest(1,1));
    }
}
