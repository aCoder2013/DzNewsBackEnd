package song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.ServletContext;

@SpringBootApplication
@EnableScheduling
public class NewsServiceEndApplication   {


    public static void main(String[] args) {
        SpringApplication.run(NewsServiceEndApplication.class, args);
    }
}