package song.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 新闻列表项
 * Created by Song on 2015/8/6.
 */
@Entity
public class NewsItem {

    @Id
    @GeneratedValue
    private Long id ;
    @Column
    private String title;//标题
    @Column
    private String thumbnail ;//缩略图
    @Column
    private String description; //简述
    @Column
    private String auth;//作者
    @Temporal(TemporalType.TIMESTAMP)
    @Column()
    private Date pubTime;//发表时间
    @Column
    private int comNumber;//评论数目
    @Column
    private String targerUrl ;//新闻URL
    @Column
    private String fromPublisher;//新闻来源

    @Column
    private int beenRead ;  //阅读数

    @OneToOne
    private NewsDetail newsDetail;


    public NewsItem() {
    }

    public NewsItem(Long id,String title, String thumbnail, String description, String auth, Date pubTime, int comNumber, String targerUrl, String fromPublisher, NewsDetail newsDetail) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.description = description;
        this.auth = auth;
        this.pubTime = pubTime;
        this.comNumber = comNumber;
        this.targerUrl = targerUrl;
        this.fromPublisher = fromPublisher;
        this.newsDetail = newsDetail;
    }

    public NewsItem(String title, String thumbnail, String description, String auth, Date pubTime, int comNumber, String targerUrl,String fromPublisher) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.description = description;
        this.auth = auth;
        this.pubTime = pubTime;
        this.comNumber = comNumber;
        this.targerUrl = targerUrl;
        this.fromPublisher = fromPublisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public int getComNumber() {
        return comNumber;
    }

    public void setComNumber(int comNumber) {
        this.comNumber = comNumber;
    }

    public String getTargerUrl() {
        return targerUrl;
    }

    public void setTargerUrl(String targerUrl) {
        this.targerUrl = targerUrl;
    }

    public NewsDetail getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(NewsDetail newsDetail) {
        this.newsDetail = newsDetail;
    }

    public String getFromPublisher() {
        return fromPublisher;
    }

    public void setFromPublisher(String fromPublisher) {
        this.fromPublisher = fromPublisher;
    }

    public int getBeenRead() {
        return beenRead;
    }

    public void setBeenRead(int beenRead) {
        this.beenRead = beenRead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsItem item = (NewsItem) o;

        if (comNumber != item.comNumber) return false;
        if (title != null ? !title.equals(item.title) : item.title != null) return false;
        if (thumbnail != null ? !thumbnail.equals(item.thumbnail) : item.thumbnail != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (auth != null ? !auth.equals(item.auth) : item.auth != null) return false;
        return !(targerUrl != null ? !targerUrl.equals(item.targerUrl) : item.targerUrl != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (auth != null ? auth.hashCode() : 0);
        result = 31 * result + comNumber;
        result = 31 * result + (targerUrl != null ? targerUrl.hashCode() : 0);
        return result;
    }
}