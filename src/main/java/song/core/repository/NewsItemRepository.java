package song.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import song.core.model.NewsItem;


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
