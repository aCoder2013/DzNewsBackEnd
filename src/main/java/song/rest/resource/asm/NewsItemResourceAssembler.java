package song.rest.resource.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.util.Assert;
import song.core.model.NewsItem;
import song.rest.mvc.APIController;
import song.rest.resource.NewsItemResource;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Song on 2015/11/1.
 */
public class NewsItemResourceAssembler extends ResourceAssemblerSupport<NewsItem,NewsItemResource> {


    /**
     * Creates a new {@link ResourceAssemblerSupport} using the given controller class and resource type.
     */
    public NewsItemResourceAssembler() {
        super(APIController.class , NewsItemResource.class);
    }

    @Override
    public NewsItemResource toResource(NewsItem entity) {
        NewsItemResource resource = createResourceWithId(entity.getId(),entity);
        resource.add(new Link("/api/news/detail/"+entity.getNewsDetail().getId(),"detail"));
        return resource;
    }

    @Override
    protected NewsItemResource createResourceWithId(Object id, NewsItem entity) {
        Assert.notNull(entity);
        Assert.notNull(id);

        NewsItemResource instance = instantiateResource(entity);
        instance.add(new Link("/api/news/"+entity.getId()).withSelfRel());
        return instance;
    }

    @Override
    public List<NewsItemResource> toResources(Iterable<? extends NewsItem> entities) {
        List<NewsItemResource> resourceList = new ArrayList<>();
        for(NewsItem resource : entities){
            resourceList.add(toResource(resource));
        }
        return resourceList;
    }

    @Override
    protected NewsItemResource instantiateResource(NewsItem entity) {
        return new NewsItemResource(entity);
    }
}
