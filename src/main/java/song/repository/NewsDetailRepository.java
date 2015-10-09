package song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import song.model.NewsDetail;

/**
 * Created by Song on 2015/8/6.
 */
@Transactional
public interface NewsDetailRepository extends JpaRepository<NewsDetail,Long> {
}
