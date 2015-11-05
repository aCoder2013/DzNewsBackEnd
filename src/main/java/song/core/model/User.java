package song.core.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Song on 2015/10/31.
 */
@JsonAutoDetect
public class User extends BaseEntity {
    private static final long serialVersionUID = 1162584138313350068L;


    @Column(length = 30)
    private String name ; //昵称

    @Column(unique = true)
    private String email;

    private String password;

    private String loacation ;

    private List<Comment> comments;

    @OneToMany(cascade =CascadeType.ALL,fetch = FetchType.LAZY,mappedBy ="user")
    @JsonIgnore
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getLoacation() {
        return loacation;
    }

    public void setLoacation(String loacation) {
        this.loacation = loacation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
