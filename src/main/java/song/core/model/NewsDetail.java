package song.core.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.*;

/**
 * 新闻详情
 * Created by Song on 2015/8/6.
 */
@Entity
@Table(name = "news_detail")
@JsonAutoDetect
public class NewsDetail extends BaseEntity{

    private static final long serialVersionUID = 3078316919038400285L;

    @Id
    @GeneratedValue
    private Long  id ;
    @Column
    private String title;
    @Column
    private String auth;//作者
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column()
    private Date pubTime;//发表时间
    @Lob
    private String content;
    @Column
    private int comNumber;//评论数目

    @OneToMany(cascade =CascadeType.ALL,fetch = FetchType.EAGER,mappedBy ="detail")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    public NewsDetail() {
    }

    public NewsDetail(String title, String content, int comNumber) {
        this.title = title;
        this.content = content;
        this.comNumber = comNumber;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getComNumber() {
        return comNumber;
    }

    public void setComNumber(int comNumber) {
        this.comNumber = comNumber;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsDetail detail = (NewsDetail) o;

        if (!title.equals(detail.title)) return false;
        return !(content != null ? !content.equals(detail.content) : detail.content != null);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
