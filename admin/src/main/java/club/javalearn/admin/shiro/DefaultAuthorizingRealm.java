package club.javalearn.admin.shiro;

import club.javalearn.admin.model.Permission;
import club.javalearn.admin.model.Role;
import club.javalearn.admin.model.User;
import club.javalearn.admin.service.UserService;
import club.javalearn.admin.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018/6/22
 * Time: 下午5:40
 * Description: No Description
 */
@Slf4j
public class DefaultAuthorizingRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;


    /**
     * 认证.登录
     *
     * @param token 用户信息
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName;
        User user;
        if (token instanceof JwtToken) {
            JwtToken jwtToken = (JwtToken) token;
            userName = JwtUtil.getUsername(jwtToken.getCredentials().toString());
            if (userName == null) {
                throw new AuthenticationException("token invalid");
            }
            user = userService.findByUserName(userName);
            if (user == null) {
                throw new AuthenticationException("User didn't existed!");
            }
            if (!JwtUtil.verify(jwtToken.getCredentials().toString(), userName, user.getPassword())) {
                throw new AuthenticationException("Username or password error");
            }
        } else {
            //获取用户输入的token
            UsernamePasswordToken utoken = (UsernamePasswordToken) token;
            userName = utoken.getUsername();
            user = userService.findByUserName(userName);
        }
        //放入shiro.调用CredentialsMatcher检验密码
        return new SimpleAuthenticationInfo(user.getPassword(), user.getPassword(), this.getClass().getName());
    }

    /**
     * 授权
     *
     * @param principal 用户登录信息
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //获取session中的用户
        User user = (User) principal.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissions = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if (roles.size() > 0) {
            for (Role role : roles) {
                Set<Permission> permissionList = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissionList)) {
                    for (Permission permission : permissionList) {
                        if (StringUtils.isNoneBlank(permission.getExpression())) {
                            permissions.add(permission.getExpression());
                        } else {
                            log.error("{} 未配置 expression 属性", permission.getPermissionName());
                        }

                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //将权限放入shiro中.
        info.addStringPermissions(permissions);
        return info;
    }


    @Override
    public String getName() {
        return "DefaultAuthorizingRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken || token instanceof UsernamePasswordToken;
    }
}
