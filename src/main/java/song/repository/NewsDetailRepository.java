package song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import song.model.NewsDetail;

/**
 * Created by Song on 2015/8/6.
 */
@Transactional
public interface NewsDetailRepository extends JpaRepository<NewsDetail,Long> {


    /**
     * 根据NewsItem.id查询对应的NewsDetail
     * @param id
     * @return
     */
    @Query(value = "select * from news_detail  where id = (select n.news_detail_id from news_item   n where id =?)",nativeQuery = true)
    NewsDetail findNewsDetailByNewsItemId(Long id);
    
}
