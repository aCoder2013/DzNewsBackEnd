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
import song.core.repository.AdminRepository;

import javax.validation.Valid;

/**
 * Created by Song on 2015/11/9.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    /**
     * 注册新管理员
     * @param admin
     * @param result
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Admin> regisiter(@Valid Admin admin, BindingResult result){
        if(result.hasErrors()){
            throw new PersonalBadRequestException(result.getAllErrors());
        }
        return ResponseEntity.ok(adminRepository.save(admin));
    }
}
