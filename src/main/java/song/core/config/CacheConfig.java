package song.core.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by Song on 2016/2/24.
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

        // Defaults
        redisConnectionFactory.setHostName("127.0.0.1");
        redisConnectionFactory.setPort(6379);
        return redisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(cf);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

        // Number of seconds before expiration. Defaults to unlimited (0)
        cacheManager.setDefaultExpiration(2 * 60 * 60);
        return cacheManager;
    }

//    @Bean
//    public JCacheManagerCustomizer jCacheManagerCustomizer(){
////        JCacheManagerFactoryBean managerFactoryBean = new JCacheManagerFactoryBean();
////        managerFactoryBean.setCacheManagerUri(URI.create(""));
////        managerFactoryBean.getObject();
//        return new JCacheManagerCustomizer() {
//            @Override
//            public void customize(javax.cache.CacheManager cacheManager) {
//                cacheManager.createCache(NewsItemService.CACHE_ITEMS,new MutableConfiguration<>().setStatisticsEnabled(true));
//                cacheManager.createCache(NewsDetailService.CACHE_DETAILS,new MutableConfiguration<>().setStatisticsEnabled(true));
//            }
//        };
//    }

}
