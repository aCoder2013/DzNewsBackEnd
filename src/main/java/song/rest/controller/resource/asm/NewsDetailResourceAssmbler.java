package song.rest.controller.resource.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import song.core.model.NewsDetail;
import song.rest.controller.mvc.APIController;
import song.rest.controller.resource.NewsDetailResource;


/**
 * Created by Song on 2015/11/1.
 */
public class NewsDetailResourceAssmbler extends ResourceAssemblerSupport<NewsDetail,NewsDetailResource> {

    /**
     * Creates a new {@link ResourceAssemblerSupport} using the given controller class and resource type.
     */
    public NewsDetailResourceAssmbler() {
        super(APIController.class, NewsDetailResource.class);
    }

    @Override
    public NewsDetailResource toResource(NewsDetail entity) {
        NewsDetailResource resource = createResourceWithId(entity.getId(), entity);
        return resource;
    }


    @Override
    protected NewsDetailResource instantiateResource(NewsDetail entity) {
        return new NewsDetailResource(entity);
    }
}
