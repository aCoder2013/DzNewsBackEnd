package song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.cache.configuration.MutableConfiguration;
import java.text.SimpleDateFormat;

/**
 * 启动类
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableSpringDataWebSupport
public class NewsServiceEndApplication   extends WebMvcConfigurerAdapter {


    public static void main(String[] args) {
        SpringApplication
                .run(NewsServiceEndApplication.class, args);
    }


    /*@Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(80);
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound"));
        factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/servererror"));
        return factory;
    }*/

    @Bean
    public JCacheManagerCustomizer jCacheManagerCustomizer(){
        return new JCacheManagerCustomizer() {
            @Override
            public void customize(javax.cache.CacheManager cacheManager) {
                cacheManager.createCache("items",new MutableConfiguration<>().setStatisticsEnabled(true));
                cacheManager.createCache("details",new MutableConfiguration<>().setStatisticsEnabled(true));
            }
        };
    }


    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
        b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return b;
    }

}