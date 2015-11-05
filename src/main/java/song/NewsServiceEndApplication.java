package song;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.ehcache.CacheManager;
import org.ehcache.EhcacheManager;
import org.ehcache.config.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.cache.configuration.MutableConfiguration;
import javax.sql.DataSource;
import java.net.URI;
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
//        JCacheManagerFactoryBean managerFactoryBean = new JCacheManagerFactoryBean();
//        managerFactoryBean.setCacheManagerUri(URI.create(""));
//        managerFactoryBean.getObject();
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

/*    @Bean
    public ServletRegistrationBean druidServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid*//*");
    }*/


    /**
     * Druid DataSource
     * @param driver
     * @param url
     * @param username
     * @param password
     * @return
     */
    @Bean
    public DataSource druidDataSource(@Value("${spring.datasource.driver-class-name}") String driver,
                                      @Value("${spring.datasource.url}") String url,
                                      @Value("${spring.datasource.username}") String username,
                                      @Value("${spring.datasource.password}") String password){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(60000);
        dataSource.setInitialSize(1);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        return dataSource;
    }

}