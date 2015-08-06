package song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.model.NewsDetail;

/**
 * Created by Song on 2015/8/6.
 */
public interface NewsDetailRepository extends JpaRepository<NewsDetail,Long> {
}
