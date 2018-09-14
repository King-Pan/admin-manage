package club.javalearn.admin.controller;

import club.javalearn.admin.common.ServerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018/9/14
 * Time: 下午3:45
 * Description: No Description
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @GetMapping("/aaaaa")
    @ResponseBody
    public Object list() {
        System.out.println("/user/list");
        ServerResponse serverResponse = ServerResponse.createBySuccess("查询成功", "Hello Shior JWT");
        return serverResponse;
        //return new ModelAndView("login").toString();
    }
}
