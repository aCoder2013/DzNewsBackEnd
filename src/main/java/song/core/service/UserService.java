package song.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.core.exception.UserEmailExistException;
import song.core.exception.UserNameExistException;
import song.core.exception.UserNotExistException;
import song.core.model.User;
import song.core.repository.UserRepository;
import song.core.utils.GravatarUtil;

/**
 * Created by Song on 2015/11/10.
 */
@Service

@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public User findById(Long id ){
        User user = userRepository.findOne(id);
        if(null==user) throw  new UserNotExistException();
        return user;
    }


    /**
     * 注册新用户
     *
     * @param user
     */
    @Transactional(readOnly = false)
    public User register(User user) {
        if(userRepository.findByName(user.getName())!=null){
            throw new UserNameExistException();
        }
        if(userRepository.findByEmail(user.getEmail())!=null){
            throw new UserEmailExistException();
        }
        user.setThumbnail(GravatarUtil.getHeadPortrait(user.getEmail()));//设置头像
        User userSaved = userRepository.save(user);
        return userSaved;
    }


    public User login(String emaill ,String email){
        User user = userRepository.findByEmailAndPassword(emaill,email);
        return user;
    }



}
