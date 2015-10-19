package song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import song.exception.GlobalDefaultExceptionHandler;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 启动类
 */
@SpringBootApplication
@EnableScheduling
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


}