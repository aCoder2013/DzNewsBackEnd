package song.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import song.core.model.Admin;

/**
 * 用户相关数据库操作接口
 * Created by Song on 2015/6/12.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    /**
        通过邮箱和密码查询用户
        @param:email
        @param:password
     */
    Admin findByEmailAndPassword(String email,String password);
}
