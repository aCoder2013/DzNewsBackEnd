package song.rest.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.cfg.beanvalidation.BeanValidationIntegrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import song.core.exception.PersonalBadRequestException;
import song.core.model.Admin;
import song.core.repository.AdminRepository;
import song.rest.AdminError;
import song.rest.util.BeanValidators;

import javax.validation.Valid;
import java.util.List;

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
