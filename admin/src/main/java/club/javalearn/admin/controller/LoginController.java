package club.javalearn.admin.controller;

import club.javalearn.admin.common.ServerResponse;
import club.javalearn.admin.model.User;
import club.javalearn.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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


    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

//    @RequestMapping("/login2")
//    public Object login3(@RequestParam("userName") String userName, @RequestParam("password") String password) {
//        ServerResponse serverResponse = ServerResponse.createBySuccessMessage("登录成功");
//        log.info("用户名:{},密码:{}", userName, password);
//        return serverResponse;
//    }


    @RequestMapping("/login")
    public Object login2(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        ServerResponse serverResponse = ServerResponse.createBySuccessMessage("登录成功");
        Boolean rememberMe = true;
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到xxRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            session.setAttribute("user", user);
            userService.updateLastLoginTime(user);
            if (log.isDebugEnabled()) {
                log.debug("登录用户为{}", user.getUserName());
            }

        } catch (Exception e) {
            log.error("登录失败，用户名[{}]", userName, e);
            token.clear();
        }
        return serverResponse;
    }


    @RequestMapping("/loginPage")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }
}
