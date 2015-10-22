package song.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import song.model.NewsDetail;

import javax.cache.annotation.*;

/**
 * Created by Song on 2015/8/6.
 */
@Repository
public interface NewsDetailRepository extends JpaRepository<NewsDetail,Long> {

    @Override
    NewsDetail findOne(Long aLong);

    @Override
    void delete(Long aLong);

    @Override
    void delete(NewsDetail newsDetail);

    /**
     * 根据NewsItem.id查询对应的NewsDetail
     * @param id
     * @return
     */
    @Query(value = "select * from news_detail  where id = (select n.news_detail_id from news_item   n where id =?)",nativeQuery = true)
    NewsDetail findNewsDetailByNewsItemId(Long id);

}
