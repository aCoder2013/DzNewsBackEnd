package song.spider;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import song.model.NewsDetail;
import song.model.NewsItem;
import song.repository.NewsDetailRepository;
import song.repository.NewsItemRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 收集雷锋网的新闻
 * Created by Song on 2015/8/6.
 */
public class CollectLeiFengNews implements AutoCollectNews{

    private Logger logger = Logger.getLogger(CollectLeiFengNews.class);

    private String url ="http://www.leiphone.com/";

    private NewsItemRepository newsItemRepository;
    private NewsDetailRepository newsDetailRepository;

    private Document doc  = null;

    private List<NewsItem> itemList ;
    public CollectLeiFengNews() {
    }

    public CollectLeiFengNews(NewsItemRepository newsItemRepository, NewsDetailRepository newsDetailRepository) {
        this.newsItemRepository = newsItemRepository;
        this.newsDetailRepository = newsDetailRepository;
    }
    /*
        收集新闻
     */
    @Override
    public void collect() {
        itemList = ParseNews();//解析新闻列表
        if(itemList!=null) {
            newsItemRepository.save(itemList);//保存新闻
        }
    }
        /*
            解析新闻详情页面
         */
        private NewsDetail parseDetail(String url) {
            NewsDetail newsDetail = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //如果为文字形式，则解析
            Element index_main = doc.select("div.index-main").addClass("lph-main").addClass("clr").first();
            if (index_main != null) {
                //新闻标题
                String title = index_main.select("div.pageTop").first().getElementsByTag("h1").text();
                Element contentElement = index_main.select("div.pageCont").addClass("lph-article-comView").first();
                Element procard = contentElement.select("div.lp-proCard").addClass("clr").first();
                if(procard!=null){
                    procard.remove();
                }
                String content = contentElement.html();
                int comNumber = Integer.parseInt(doc.select("div.pi-comment").first().getElementsByTag("span").first().text());
                newsDetail  = new NewsDetail(title, content,comNumber);
                //解析评论
                Element articleCommentContainer = index_main.select("div.article-comment-container").first();

                return newsDetail;
            }
            else if(doc.select("div.photoActicle-main").addClass("lph-main").addClass("clr").first()!=null){
             /*
                如果为轮播图片的形式
             */
                return null;
            }else {
                return null;
            }
        }


    /*
        根据置指定URL解析新闻数据
     */
    private List<NewsItem> ParseNews() {
        List<NewsItem> itemList = new ArrayList<>();
        List<NewsItem> itemInDB = newsItemRepository.findAll();//找到在数据库中的所有数据
        for(NewsItem item:itemInDB){
            item.setId(null);//将ID置为空
        }
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
            Element img = e.select("img.lazy").first();
            String thumbnail = img.attr("data-original");
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
            NewsItem news = new NewsItem(title, thumbnail,desc,auth, pubTime, comNumber, targetUrl);
            news.setId(null);
            if(!itemInDB.contains(news)){ //如果数据库中已经存在这条新闻，则不添加到列表中
                NewsDetail detail = parseDetail(news.getTargerUrl());//获取新闻详情
                //如果新闻详情不为空才添加到列表中
                if(detail!=null) {
                    newsDetailRepository.save(detail);
                    news.setNewsDetail(detail);//建立关联关系
                    itemList.add(news);
                }
            }
        }
        return itemList;
    }
}
