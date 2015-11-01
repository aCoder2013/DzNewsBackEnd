package song.core.repository;

import org.springframework.data.repository.CrudRepository;
import song.core.model.Comment;

/**
 * Created by Song on 2015/10/27.
 */
public interface CommentRepository extends CrudRepository<Comment,Long> {
}
