package song.core.config;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import song.core.service.NewsDetailService;
import song.core.service.NewsItemService;

import javax.cache.configuration.MutableConfiguration;

/**
 * Created by Song on 2016/2/24.
 */
@Configuration
public class CacheConfig {



    @Bean
    public JCacheManagerCustomizer jCacheManagerCustomizer(){
//        JCacheManagerFactoryBean managerFactoryBean = new JCacheManagerFactoryBean();
//        managerFactoryBean.setCacheManagerUri(URI.create(""));
//        managerFactoryBean.getObject();
        return new JCacheManagerCustomizer() {
            @Override
            public void customize(javax.cache.CacheManager cacheManager) {
                cacheManager.createCache(NewsItemService.CACHE_ITEMS,new MutableConfiguration<>().setStatisticsEnabled(true));
                cacheManager.createCache(NewsDetailService.CACHE_DETAILS,new MutableConfiguration<>().setStatisticsEnabled(true));
            }
        };
    }

}
