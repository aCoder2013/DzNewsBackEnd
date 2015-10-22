package song.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import song.model.NewsItem;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;


/**
 * Created by Song on 2015/8/6.
 */
@Repository
public interface NewsItemRepository extends JpaRepository<NewsItem,Long> {


    @Override
    NewsItem findOne(Long id);

    @Override
    void delete(Long id);

    @Override
    void delete(NewsItem item);

    /**
        Sort By Publish Time
     */
    @Query("SELECT n FROM NewsItem n order by pubTime desc ")
    Page<NewsItem> findAllByOrderByPubTime(Pageable pageable);





}
