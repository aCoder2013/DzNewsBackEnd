package song.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import song.core.exception.PersonalBadRequestException;
import song.core.model.Admin;
import song.core.model.User;
import song.core.service.UserService;
import song.rest.form.WrapForm;

import javax.validation.Valid;

/**
 * Created by Song on 2015/11/17.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 注册新用户
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<User> regisiter(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            throw new PersonalBadRequestException(result.getAllErrors());
        }
        return ResponseEntity.ok(userService.register(user));
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<WrapForm> login(String email ,String password){
        User user = userService.login(email,password);
        if(user==null){
            return new ResponseEntity<WrapForm>(new WrapForm(0,"邮箱或者密码不正确"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WrapForm>(new WrapForm(1,user), HttpStatus.OK );
    }
}



