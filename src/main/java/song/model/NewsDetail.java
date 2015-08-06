package song.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Song on 2015/8/6.
 */
@Entity
public class NewsDetail {

    @Id
    @GeneratedValue
    private Long  id ;
    @Column
    private String title;
    @Lob
    private String content;
    @Column
    private int comNumber;//评论数目

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsDetail that = (NewsDetail) o;

        if (comNumber != that.comNumber) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return !(content != null ? !content.equals(that.content) : that.content != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + comNumber;
        return result;
    }
}
