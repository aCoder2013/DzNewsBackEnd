package song.core.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull(message = "昵称不能为空")
    @Size(min =3,message = "昵称需要至少3个字符")
    private String name ;

    @Column
    @NotNull(message = "邮箱不能为空")
    @Email
    private String email ;

    @Column
    @NotNull(message = "内容不能为空")
    @SafeHtml
    private String content ;

    @Column
    private String location ;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional =true)
    @JoinColumn(name = "news_detail_id")
    @JsonIgnore
    @NotFound(action = NotFoundAction.IGNORE)
    private NewsDetail detail;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY )
    private User user;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
