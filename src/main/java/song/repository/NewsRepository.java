package song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.model.News;

/**
 * Created by Song on 2015/6/13.
 */
public interface NewsRepository extends JpaRepository<News,Long> {

}
