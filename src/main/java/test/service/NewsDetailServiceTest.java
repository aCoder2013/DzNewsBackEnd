package test.service;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import song.NewsServiceEndApplication;
import song.core.model.NewsDetail;
import song.core.service.NewsDetailService;

import java.util.List;

/**
 * Created by Song on 2015/11/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(NewsServiceEndApplication.class)
public class NewsDetailServiceTest {

    @Autowired
    private NewsDetailService detailService;


    @Test
    public void test(){
        List<NewsDetail> detailList = Lists.newArrayList();
        detailList.add(new NewsDetail("a", "a", 1));
        detailList.add(new NewsDetail("c","b",2));
        detailService.save(detailList);
    }
}
