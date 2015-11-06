package song.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import song.core.model.NewsDetail;

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
    @Query(value = "select * from news_detail  where id = (select n.news_detail_id from news_item n where id =?)",nativeQuery = true)
    NewsDetail findNewsDetailByNewsItemId(Long id);

}
