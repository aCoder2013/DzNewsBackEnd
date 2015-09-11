package song.model;

import javax.persistence.*;
import java.util.Date;

/**个人发布的新闻
 * Created by Song on 2015/6/13.
 */
@Entity
public class PersonalNews {

    @Id
    @GeneratedValue
    private Long id;
    //标题
    @Column
    private String title;
    //内容
    @Lob
    private String content;
    //来源
    @Column
    private String fromPublisher;
    //发布时间
    @Column
    private Date  publishTime;
    //阅读数
    @Column
    private int beenRead;


    public PersonalNews(){}

    public PersonalNews(Long id ,String title, String content, String fromPublisher, Date publishTime, int beenRead) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.fromPublisher = fromPublisher;
        this.publishTime = publishTime;
        this.beenRead = beenRead;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromPublisher() {
        return fromPublisher;
    }

    public void setFromPublisher(String fromPublisher) {
        this.fromPublisher = fromPublisher;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
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

        PersonalNews news = (PersonalNews) o;

        if (beenRead != news.beenRead) return false;
        if (id != null ? !id.equals(news.id) : news.id != null) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (content != null ? !content.equals(news.content) : news.content != null) return false;
        if (fromPublisher != null ? !fromPublisher.equals(news.fromPublisher) : news.fromPublisher != null)
            return false;
        return !(publishTime != null ? !publishTime.equals(news.publishTime) : news.publishTime != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (fromPublisher != null ? fromPublisher.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + beenRead;
        return result;
    }

    @Override
    public String toString() {
        return "PersoanlNews{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", fromPublisher='" + fromPublisher + '\'' +
                ", publishTime=" + publishTime +
                ", beenRead=" + beenRead +
                '}';
    }
}
