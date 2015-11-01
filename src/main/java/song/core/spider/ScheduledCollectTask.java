package song.core.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import song.core.repository.NewsDetailRepository;
import song.core.repository.NewsItemRepository;

/**
 * 定时抓取新闻
 * Created by Song on 2015/8/6.
 */
@Component
public class ScheduledCollectTask {
    private Logger logger = LoggerFactory.getLogger(ScheduledCollectTask.class);

    //时间间隔,在此我们设置为6个小时
    private static final long PERIOD_DAY = 1 * 60 * 60 * 1000;

    @Autowired
    private NewsItemRepository newsItemRepository;

    @Autowired
    private NewsDetailRepository newsDetailRepository;

    @Scheduled(fixedRate = PERIOD_DAY)
    public void autoCollectNews(){
        AutoCollectNews collectNews = new CollectLeiFengNews(newsItemRepository,newsDetailRepository);
        logger.info("Start To Collect News");
        collectNews.collect(CollectLeiFengNews.LEIFENG_NEWS_URL);//抓取雷锋网的新闻
        logger.info("Finish To Collect News");
    }
}
