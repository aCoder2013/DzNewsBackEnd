package song.spider;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import song.model.NewsItem;
import song.repository.NewsItemRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 收集雷锋网的新闻
 * Created by Song on 2015/8/6.
 */
public class CollectLeiFengNews implements AutoCollectNews{

    private Logger logger = Logger.getLogger(CollectLeiFengNews.class);

    private String url ="http://www.leiphone.com/";

    private NewsItemRepository newsItemRepository;

    public CollectLeiFengNews() {
    }

    public CollectLeiFengNews(NewsItemRepository newsItemRepository) {
        this.newsItemRepository = newsItemRepository;
    }

    @Override
    public void collect() {
        List<NewsItem> itemSet = ParseNews();
        if(itemSet!=null) {
            newsItemRepository.save(itemSet);//保存新闻
        }
    }



    /*
        根据置指定URL解析新闻数据
     */
    private List<NewsItem> ParseNews() {
        List<NewsItem> itemSet = new ArrayList<>();
        List<NewsItem> itemInDB = newsItemRepository.findAll();//找到在数据库中的所有数据
        for(NewsItem item:itemInDB){
            item.setId(null);//将ID置为空
        }
        Document doc = null;
        try {
            doc= Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element pageList = doc.select("div.lph-pageList").addClass("index-pageList").first();
        Element newsList = pageList.select("div.wrap").first();
        Element ul = newsList.getElementsByTag("ul").first();
        Elements li = ul.select("li.pbox").addClass("clr");
        for(Element e :li){
            Element word = e.select("div.word").first();
            Element titleElement = word.select("a[href]").first();
            String targetUrl = titleElement.attr("href");
            String title = titleElement.select("div.tit").text();
            String desc = word.select("div.des").first().text();
            Element infoElement = word.select("div.info").first();
            Element aut = infoElement.select("div.aut").first();
            String auth = aut.select("a").select("span").text();
            Element time = infoElement.select("div.time").first();
            Elements timeSpan = time.getElementsByTag("span");
            String pubTime ="";
            for(Element temp : timeSpan){
                pubTime+=temp.text()+" ";
            }
            int comNumber =Integer.parseInt(infoElement.select("a.cmt").first().getElementsByTag("span").text());
            NewsItem news = new NewsItem(title,desc,auth, pubTime, comNumber, targetUrl);
            news.setId(null);
            if(!itemInDB.contains(news)){ //如果数据库中已经存在这条新闻，则不添加到列表中
                itemSet.add(news);
            }
        }
        return itemSet;
    }
}
