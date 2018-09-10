package club.javalearn.admin.controller;

import club.javalearn.admin.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018/9/10
 * Time: 上午11:36
 * Description: No Description
 */
@Slf4j
@RestController
@RequestMapping("/sso")
public class LoginController {

    @RequestMapping("/login")
    public Object login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        ServerResponse serverResponse = ServerResponse.createBySuccessMessage("登录成功");
        log.info("用户名:{},密码:{}", userName, password);
        return serverResponse;
    }
}
