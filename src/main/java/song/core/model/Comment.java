package song.core.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 评论
 * Created by Song on 2015/10/27.
 */
@Entity
@JsonAutoDetect
public class Comment extends BaseEntity {
    private static final long serialVersionUID = 3334651482912441069L;




    @Column
    @NotNull
    private String name ;


    @Column
    @NotNull
    private String content ;

    @Column
    private String location ;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional =true)
    @JoinColumn(name = "news_detail_id")
    @JsonIgnore
    @NotFound(action = NotFoundAction.IGNORE)
    private NewsDetail detail;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date  pub_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getPub_time() {
        return pub_time;
    }

    public void setPub_time(Date pub_time) {
        this.pub_time = pub_time;
    }

    public NewsDetail getDetail() {
        return detail;
    }

    public void setDetail(NewsDetail detail) {
        this.detail = detail;
    }


}
