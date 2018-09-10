package club.javalearn.admin.shiro;

import club.javalearn.admin.model.User;
import club.javalearn.admin.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018/9/10
 * Time: 下午3:06
 * Description: No Description
 */
@Slf4j
@Configuration
public class CustomRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }


    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("---------------------------->开始身份认证 begin<----------------------------");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String userName = JwtUtil.getUsername(token);
        if (userName == null || !JwtUtil.verify(token, userName)) {
            throw new AuthenticationException("token认证失败！");
        }
        User user = new User();
        if(user==null || user.getPassword()==null){
            throw new AuthenticationException("用户密码错误!");
        }

        if(user.getStatus().equals("0")){

        }


        System.out.println(token);

        log.info("---------------------------->开始身份认证   end<----------------------------");
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
