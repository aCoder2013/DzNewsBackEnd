package song.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;

/**
 * Created by Song on 2015/11/17.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<User> regisiter(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            throw new PersonalBadRequestException(result.getAllErrors());
        }
        return ResponseEntity.ok(userService.register(user));
    }
}



