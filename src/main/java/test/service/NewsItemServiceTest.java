package test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import song.NewsServiceEndApplication;
import song.core.model.NewsItem;
import song.core.service.NewsItemService;

import java.util.List;

/**
 * Created by Song on 2015/10/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsServiceEndApplication.class)
public class NewsItemServiceTest {

    @Autowired
    private NewsItemService itemService;

    @Test
    public void testFindOne(){
        NewsItem item = itemService.findOne(693L);
        Assert.notNull(item);
    }

    @Test
    public void testFindRecentNews() throws Exception {
        List<NewsItem> itemList = itemService.findRecentNews(new PageRequest(1,10));
        Assert.notNull(itemList);
    }
}
