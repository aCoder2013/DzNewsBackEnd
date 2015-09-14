package song.spider;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import song.repository.NewsDetailRepository;
import song.repository.NewsItemRepository;

/**
 * 定时抓取新闻
 * Created by Song on 2015/8/6.
 */
@Component
public class ScheduledCollectTask {
    private Logger logger = Logger.getLogger(ScheduledCollectTask.class);

    //时间间隔,在此我们设置为6个小时
    private static final long PERIOD_DAY = 6 * 60 * 60 * 1000;

    @Autowired
    private NewsItemRepository newsItemRepository;

    @Autowired
    private NewsDetailRepository newsDetailRepository;

    @Scheduled(fixedRate = PERIOD_DAY)
    public void autoCollectNews(){
        AutoCollectNews collectNews = new CollectLeiFengNews(newsItemRepository,newsDetailRepository);
        logger.warn("Start To Collect News");
        collectNews.collect();
        logger.debug("Finish To Collect News");
    }
}
