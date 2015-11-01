package song.rest.resource;

import org.springframework.hateoas.ResourceSupport;
import song.core.model.NewsItem;
import java.util.Date;

/**
 * Created by Song on 2015/11/1.
 */
public class NewsItemResource extends ResourceSupport {


    private NewsItem item;


    public NewsItemResource(NewsItem item) {
        this.item = item;
    }


    public NewsItem getItem(){
        return this.item;
    }


}
