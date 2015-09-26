package song.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import song.model.NewsDetail;
import song.model.NewsItem;
import song.repository.NewsDetailRepository;
import song.repository.NewsItemRepository;
import song.utils.NewsItemBuilder;
import song.utils.TextExtract;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Song on 2015/9/26.
 */
public class CollectRegularNews implements AutoCollectNews{

    private NewsItemRepository newsItemRepository;
    private NewsDetailRepository newsDetailRepository;


    public CollectRegularNews(NewsItemRepository newsItemRepository, NewsDetailRepository newsDetailRepository) {
        this.newsDetailRepository = newsDetailRepository;
        this.newsItemRepository = newsItemRepository;
    }

    /**
     * 提取一些常规主题网站的正文：新闻，博客等
     * @param url
     */
    @Override
    public void collect(String url) {
        Document doc ;
        try {
            doc = Jsoup.connect(url).get();
            String title = doc.title();
            String content = TextExtract.parse(doc.body().toString());
            NewsItemBuilder builder = new NewsItemBuilder();
            NewsItem item = builder.setTitle(title)
                    .setPubTime(new Date())
                    .setTargetUrl(url)
                    .newsInstance();
            NewsDetail detail = new NewsDetail(title,content,0);
            item.setNewsDetail(detail);
            newsDetailRepository.save(detail);
            newsItemRepository.save(item);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
