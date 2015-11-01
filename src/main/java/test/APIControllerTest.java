package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import song.NewsServiceEndApplication;

import java.util.List;



/**
 * Created by Song on 2015/10/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(NewsServiceEndApplication.class)
@WebIntegrationTest("server.port:0")
public class APIControllerTest {


    private RestTemplate template;

    @Value("${local.server.port}")   // 6
    private int port;
    @Before
    public void setup(){
        template = new TestRestTemplate();
    }

    @Test
    public void  test() throws Exception {
        List entity;
        entity = template.getForEntity("http://localhost:"+port+"/api/news", List.class).getBody();
        Assert.notEmpty(entity);
    }


}
