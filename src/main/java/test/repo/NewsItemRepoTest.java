package test.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import song.NewsServiceEndApplication;
import song.core.model.NewsItem;
import song.core.repository.NewsItemRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Song on 2015/11/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(NewsServiceEndApplication.class)
public class NewsItemRepoTest {


    @Autowired
    private NewsItemRepository repository;


    @Test
    public void  test(){
        Page<NewsItem> pageList = repository.findAllByOrderByPubTime(new PageRequest(1, 10));
        List<NewsItem> list = pageList.getContent();
        System.out.println(Arrays.toString(list.toArray()));
    }


}
