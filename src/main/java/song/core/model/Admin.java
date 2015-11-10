package song.core.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 用户表
 * Created by Song on 2015/6/12.
 */
@Entity(name = "admin")
@JsonAutoDetect
@JsonIgnoreProperties(value = {"password","id"})
public class Admin extends BaseEntity {
    private static final long serialVersionUID = 1103848239243405667L;


    @Column
    @NotNull(message = "用户名不能为空")
    @Size(min=3,max = 16,message = "用户名长度至少为三个字符")
    private String name; //用户名

    @Column
    @NotNull(message = "密码不能为空")
    @Size(min = 6,max = 20,message = "密码长度不正确，必须要在6-20个字符之间")
    private String password;//密码

    @Column(nullable = false)
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
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
