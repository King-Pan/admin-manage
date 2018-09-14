package club.javalearn.admin.utils;

import club.javalearn.admin.model.User;
import club.javalearn.admin.properties.ShiroProperties;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018/9/11
 * Time: 下午1:42
 * Description: No Description
 */
@Service
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Autowired
    private ShiroProperties shiroProperties;


    public String encryptPassword(User user, String password) {
        String newPassword = new SimpleHash(
                //加密算法
                shiroProperties.getPassword().getAlgorithmName(),
                //密码
                password,
                //salt盐   username + salt
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                //迭代次数
                shiroProperties.getPassword().getHashIterations()
        ).toHex();
        return newPassword;
    }

    public void encryptPassword(User user, Boolean isNew) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String password;
        if (isNew) {
            password = shiroProperties.getPassword().getDefaultPassword();
        } else {
            password = user.getPassword();
        }

        String newPassword = new SimpleHash(
                //加密算法
                shiroProperties.getPassword().getAlgorithmName(),
                //密码
                password,
                //salt盐   username + salt
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                //迭代次数
                shiroProperties.getPassword().getHashIterations()
        ).toHex();

        user.setPassword(newPassword);
    }
}
