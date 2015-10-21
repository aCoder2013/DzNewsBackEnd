package song.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import song.model.NewsItem;


/**
 * Created by Song on 2015/8/6.
 */
@Transactional
public interface NewsItemRepository extends JpaRepository<NewsItem,Long> {




    /**
        Sort By Publish Time
     */
    @Query("SELECT n FROM NewsItem n order by pubTime desc ")
    Page<NewsItem> findAllByOrderByPubTime(Pageable pageable);




}
