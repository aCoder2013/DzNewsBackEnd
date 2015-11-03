package song.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import song.core.service.NewsDetailService;
import song.core.service.NewsItemService;
import song.rest.resource.NewsDetailResource;
import song.rest.resource.NewsItemResource;
import song.rest.resource.asm.NewsDetailResourceAssmbler;
import song.rest.resource.asm.NewsItemResourceAssembler;

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
    @RequestMapping(value = "/{id}")
    public ResponseEntity<NewsItemResource> news(@PathVariable("id") Long id){
         return new ResponseEntity<>(new NewsItemResourceAssembler()
                 .toResource(itemService.get(id)), HttpStatus.OK);
    }


    /**
     * API:获取单页新闻
     * @param pageable
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<List<NewsItemResource>> showNewsPage(Pageable pageable){
        return new ResponseEntity<List<NewsItemResource>>(new NewsItemResourceAssembler().toResources(itemService.findRecentNews(pageable)),HttpStatus.OK);
    }

    /**
     * API：根据指定NewsItem ID
     *      获取新闻详情
     */
    @RequestMapping(value = "/detail/{id}")
    public ResponseEntity<NewsDetailResource> showNewsDetail(@PathVariable("id")Long id){
        return new ResponseEntity<>(
                new NewsDetailResourceAssmbler()
                        .toResource(detailService.findByNewsItemId(id)),HttpStatus.OK);
    }


    @RequestMapping("/news/{id}/delete")
    public void delete(@PathVariable("id")Long id){
        itemService.delete(id);
    }


    /*@RequestMapping(value = "/detail/{id}/comment/new",method = RequestMethod.POST)
    public Comment addComment(@PathVariable("id") Long detailId,Comment comment){
            return detailService.addComment(detailId,comment);
    }

    @RequestMapping(value = "/detail/{id}/comment")
    public List<Comment> showAllComments(@PathVariable("id") Long detailId){
        return detailService.getAllComments(detailId);
    }*/
}
