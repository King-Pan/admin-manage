package club.javalearn.admin;

import club.javalearn.admin.parameter.UserInfo;
import club.javalearn.admin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApplicationTests {


    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        UserInfo userInfo = new UserInfo();
        userInfo.setCreateTime(new Date());
        userInfo.setEmail("pwpw1218@qq.com");
        userInfo.setNickName("超级管理员");
        userInfo.setPassword("123456");
        userInfo.setStatus("1");
        userInfo.setPhoneNum("18000000000");
        userInfo.setUserName("pwpw1218");
        userService.save(userInfo);
    }

}
