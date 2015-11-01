package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import song.NewsServiceEndApplication;
import song.core.model.Comment;
import song.core.model.NewsDetail;
import song.core.repository.CommentRepository;
import song.core.repository.NewsDetailRepository;

/**
 * Created by Song on 2015/10/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsServiceEndApplication.class)
public class CommentTest {

    @Autowired
    private NewsDetailRepository repository;
    @Autowired
    private CommentRepository commentRepository;


    @Test
    public void testAddComment() throws Exception {

        NewsDetail detail = repository.findOne(439L);
        Comment comment = new Comment();
        comment.setContent("阿德个");
        comment.setName("Mars");
        detail.getComments().add(comment);
        comment.setDetail(detail);
        Comment co  = commentRepository.save(comment);
        repository.save(detail);
        System.out.println(co);
    }
}
