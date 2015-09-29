package song.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 * 控制器:用户
 * Created by Song on 2015/6/12.
 */
@Controller
@SessionAttributes(value = {"user","newsList"})
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);


}
