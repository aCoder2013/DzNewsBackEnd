package song.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 雷锋网
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
    private String description; //简述
    @Column
    private String auth;//作者
    @Column
    private String pubTime;//发表时间
    @Column
    private int comNumber;//评论数目
    @Column
    private String targerUrl ;//新闻URL



    public NewsItem() {
    }

    public NewsItem(String title, String description, String auth, String pubTime, int comNumber, String targerUrl) {
        this.title = title;
        this.description = description;
        this.auth = auth;
        this.pubTime = pubTime;
        this.comNumber = comNumber;
        this.targerUrl = targerUrl;
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

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsItem item = (NewsItem) o;

        if (title != null ? !title.equals(item.title) : item.title != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        return !(auth != null ? !auth.equals(item.auth) : item.auth != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (auth != null ? auth.hashCode() : 0);
        return result;
    }
}
