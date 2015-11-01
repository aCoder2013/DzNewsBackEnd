package song.core.utils;

import song.core.model.NewsItem;

import java.util.Date;

/**
 * Created by Song on 2015/9/26.
 */
public class NewsItemBuilder {

    private  NewsItem item = new NewsItem(); ;


    public  NewsItem newsInstance(){
        return item;
    }

    public NewsItemBuilder setId(Long id){
        item.setId(id);
        return  this;
    }

    public NewsItemBuilder setTitle(String title){
        item.setTitle(title);
        return this;
    }


    public NewsItemBuilder setThumbnail(String thubmnail){
        item.setThumbnail(thubmnail);
        return this;
    }

    public NewsItemBuilder setDescription(String description){
        item.setDescription(description);
        return  this;
    }

    public NewsItemBuilder setAuth(String auth){
        item.setAuth(auth);
        return this;
    }

    public NewsItemBuilder setPubTime(Date pubtime){
        item.setPubTime(pubtime);
        return  this;
    }

    public NewsItemBuilder setComNumber(int comNumber){
        this.item.setComNumber(comNumber);
        return this;
    }

    public NewsItemBuilder setTargetUrl(String targetUrl){
        item.setTargerUrl(targetUrl);
        return this;
    }

    public NewsItemBuilder setFromPublisher(String fromPublisher){
        item.setFromPublisher(fromPublisher);
        return this;
    }

    public NewsItemBuilder setBeenRead(int beenRead){
        item.setBeenRead(beenRead);
        return this;
    }













}
