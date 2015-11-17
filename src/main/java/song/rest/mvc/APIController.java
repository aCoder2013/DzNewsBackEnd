package song.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import song.core.exception.PersonalBadRequestException;
import song.core.model.Comment;
import song.core.model.NewsItem;
import song.core.service.NewsDetailService;
import song.core.service.NewsItemService;
import song.rest.resource.NewsDetailResource;
import song.rest.resource.NewsItemResource;
import song.rest.resource.asm.NewsDetailResourceAssmbler;
import song.rest.resource.asm.NewsItemResourceAssembler;
import song.rest.util.MediaTypes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Song on 2015/10/20.
 */
@RestController
@RequestMapping("/api/news")
public class APIController {

    @Autowired
    private NewsItemService itemService;

    @Autowired
    private NewsDetailService detailService;


    /**
     * 获取指定ID新闻
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<NewsItemResource> news(@PathVariable("id") Long id){
         return new ResponseEntity<>(new NewsItemResourceAssembler()
                 .toResource(itemService.getWithNewsDetail(id)), HttpStatus.OK);
    }


    /**
     * API:
     * 获取单页新闻
     * @param pageable
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<List<NewsItemResource>> showNewsPage(Pageable pageable){
        List<NewsItemResource> itemResources = new NewsItemResourceAssembler().
                toResources(itemService.findRecentNews(pageable));
        return new ResponseEntity<>(itemResources,HttpStatus.OK);
    }




    /**
     * API：
     * 根据指定NewsDetail.Id
     * 获取新闻详情
     */
    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public ResponseEntity<NewsDetailResource> showNewsDetail(@PathVariable("id")Long id){
        return new ResponseEntity<>(
                new NewsDetailResourceAssmbler()
                        .toResource(detailService.get(id)),HttpStatus.OK);
    }

    /**
     * 删除指定新闻
     * @param id
     */
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable("id")Long id){
        itemService.delete(id);
    }


    /**
     * 添加评论到指定的新闻
     * @param detailId
     * @param comment
     * @return
     */
    @RequestMapping(value = "/detail/{id}/comment/new",method = RequestMethod.POST)
    public Comment addComment(@PathVariable("id") Long detailId,@Valid Comment comment,BindingResult result){
        if(result.hasErrors()){
            throw new PersonalBadRequestException(result.getAllErrors());
        }
        return detailService.addComment(detailId,comment);
    }

    /**
     * 获取所有评论
     * @param detailId
     * @return
     */
    @RequestMapping(value = "/detail/{id}/comment",method = RequestMethod.GET)
    public List<Comment> showAllComments(@PathVariable("id") Long detailId){
        return detailService.getAllComments(detailId);
    }
}
