package song.rest.resource.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.util.Assert;
import song.core.model.NewsDetail;
import song.rest.mvc.APIController;
import song.rest.resource.NewsDetailResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


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

        resource.add(new Link("/api/news/detail/"+entity.getId()+"/comment","comment"));
        return resource;
    }

    @Override
    protected NewsDetailResource createResourceWithId(Object id, NewsDetail entity) {
        Assert.notNull(entity);
        Assert.notNull(id);
        NewsDetailResource instance = instantiateResource(entity);
        instance.add(new Link("/api/news/detail/"+entity.getId()).withSelfRel());
        return instance;
    }

    @Override
    protected NewsDetailResource instantiateResource(NewsDetail entity) {
        return new NewsDetailResource(entity);
    }
}
