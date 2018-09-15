package club.javalearn.admin.controller;

import club.javalearn.admin.common.ServerResponse;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/list")
    @RequiresAuthentication
    public Object list() {
        System.out.println("/user/list");
        ServerResponse serverResponse = ServerResponse.createBySuccess("查询成功", "Hello Shior JWT");
        return serverResponse;
        //return new ModelAndView("login").toString();
    }

    @RequestMapping("/add")
    @RequiresRoles("admin")
    public Object add() {
        System.out.println("/user/add");
        ServerResponse serverResponse = ServerResponse.createBySuccess("add成功", "Hello Shior JWT");
        return serverResponse;
        //return new ModelAndView("login").toString();
    }
}
