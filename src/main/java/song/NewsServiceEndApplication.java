package song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 启动类
 */
@SpringBootApplication
@EnableScheduling
@EnableSpringDataWebSupport
public class NewsServiceEndApplication   extends WebMvcConfigurerAdapter {


    public static void main(String[] args) {
        SpringApplication
                .run(NewsServiceEndApplication.class, args);
    }



}