package song.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import song.model.NewsItem;

import java.util.List;


/**
 * Created by Song on 2015/8/6.
 */
public interface NewsItemRepository extends JpaRepository<NewsItem,Long> {




    /*
        Sort By Publish Time
     */
    @Query("SELECT n FROM NewsItem n order by pubTime desc ")
    List<NewsItem> findAllByOrderByPubTime(Pageable pageable);


}
