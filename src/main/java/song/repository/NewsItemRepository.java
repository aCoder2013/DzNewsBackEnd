package song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.model.NewsItem;

/**
 * Created by Song on 2015/8/6.
 */
public interface NewsItemRepository extends JpaRepository<NewsItem,Long> {
}
