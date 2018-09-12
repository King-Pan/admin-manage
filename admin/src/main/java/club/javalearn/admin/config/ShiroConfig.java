package club.javalearn.admin.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import club.javalearn.admin.filter.JwtFilter;
import club.javalearn.admin.shiro.CustomFormAuthenticationFilter;
import club.javalearn.admin.shiro.DefaultAuthorizingRealm;
import club.javalearn.admin.shiro.LoginLimitHashedCredentialsMatcher;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018/9/10
 * Time: 下午3:02
 * Description: No Description
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        //设置我们自定义的JWT过滤器
        filterMap.put("jwt", new JwtFilter());
        bean.setFilters(filterMap);

        //配置登录的url和登录成功的url
        bean.setLoginUrl("/loginPage");
        bean.setSuccessUrl("/index");
        //
        bean.setUnauthorizedUrl("/error/403");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //表示可以匿名访问
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/loginPage", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/swagger/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");

        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/h-ui/**", "anon");
        filterChainDefinitionMap.put("/h-ui.admin/**", "anon");
        //shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //表示需要认证才可以访问
        filterChainDefinitionMap.put("/*", "authc");
        //表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/*.*", "authc");
        filterChainDefinitionMap.put("/**", "jwt");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);



        return bean;
    }


    /**
     * 开启thymeleaf-extras-shiro功能
     *
     * @return ShiroDialect
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 配置核心安全事务管理器
     *
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(getDefaultAuthorizingRealm());
        return manager;
    }


    /**
     * 配置自定义的权限登录器
     */
    @Bean
    public DefaultAuthorizingRealm getDefaultAuthorizingRealm() {
        DefaultAuthorizingRealm authorizingRealm = new DefaultAuthorizingRealm();
        // 配置自定义的密码比较器
        authorizingRealm.setCredentialsMatcher(loginLimitHashedCredentialsMatcher());
        return authorizingRealm;
    }


    /**
     * 配置自定义的密码比较器
     *
     * @return LoginLimitHashedCredentialsMatcher
     */
    @Bean
    public LoginLimitHashedCredentialsMatcher loginLimitHashedCredentialsMatcher() {
        LoginLimitHashedCredentialsMatcher credentialsMatcher = new LoginLimitHashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("sha-1");
        credentialsMatcher.setHashIterations(10);
        return credentialsMatcher;
    }


    @Bean
    public CustomFormAuthenticationFilter customFormAuthenticationFilter() {
        CustomFormAuthenticationFilter formAuthenticationFilter = new CustomFormAuthenticationFilter();
        formAuthenticationFilter.setLoginUrl("/loginPage");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setUsernameParam("userName");
        return formAuthenticationFilter;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @return AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    @Bean
    public CacheManager cacheManager() {
        return CacheManager.newInstance(CacheManager.class.getClassLoader().getResource("ehcache.xml"));
    }
}
