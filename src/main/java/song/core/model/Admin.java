package song.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * 用户表
 * Created by Song on 2015/6/12.
 */
@Entity(name = "admin")
@JsonIgnoreProperties(value = {"password","id"})
public class Admin extends BaseEntity {
    private static final long serialVersionUID = 1103848239243405667L;


    @Column
    @NotNull
    private String name; //用户名

    @Column
    private String password;//密码

    @Column(nullable = false)
    @NotNull
    @Email
    private String email;//邮箱

    public Admin() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (name != null ? !name.equals(admin.name) : admin.name != null) return false;
        if (password != null ? !password.equals(admin.password) : admin.password != null) return false;
        return email.equals(admin.email);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + email.hashCode();
        return result;
    }
}
