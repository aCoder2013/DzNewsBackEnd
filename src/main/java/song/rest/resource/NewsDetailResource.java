package song.rest.resource;

import org.springframework.hateoas.ResourceSupport;
import song.core.model.NewsDetail;

/**
 * Created by Song on 2015/11/1.
 */
public class NewsDetailResource extends ResourceSupport {

    private NewsDetail detail;

    public NewsDetailResource(NewsDetail detail) {
        this.detail = detail;
    }

    public NewsDetail getDetail(){
        return this.detail;
    }


}
