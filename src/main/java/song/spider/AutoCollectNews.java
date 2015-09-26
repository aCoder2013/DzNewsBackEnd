package song.spider;



/**
 * 每一个新闻收集器都需要实现这个接口
 * Created by Song on 2015/8/6.
 */
public interface AutoCollectNews {

    /**
        收集新闻
     */
    void collect(String url);

}
