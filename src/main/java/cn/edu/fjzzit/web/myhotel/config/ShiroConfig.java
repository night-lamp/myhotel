package cn.edu.fjzzit.web.myhotel.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;


@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Autowired SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        shiroFilterFactoryBean.setLoginUrl("/user/need_login");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/unauth");
        // 权限比较低的放前面, 权限较高的放后面
        filterMap.put("/user/need_login", "anon");
        filterMap.put("/user/unauth", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("user/delUser", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/user/login", "anon");
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public SecurityManager securityManager(@Autowired MyShiroRealm myShiroRealm,@Autowired SessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(myShiroRealm);
        return securityManager;
    }
    @Bean
    public MyShiroRealm myShiroRealm(@Autowired CredentialsMatcher credentialsMatcher){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(credentialsMatcher);
        return myShiroRealm;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        hashedCredentialsMatcher.setHashIterations(3);
        return hashedCredentialsMatcher;
    }

    @Bean
    public SessionManager sessionManager(){
        MysessionManager sessionManager = new MysessionManager();
        sessionManager.setGlobalSessionTimeout(300000);
        return sessionManager;
    }

    /**
     * 管理Shiro相关Bean的生命周期。
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 扫描上下文寻找所有的Advisor（通知器），将符合条件的Advisor切入对应的Bean。
     * 需要依赖LifecycleBeanPostProcessor。
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**';45];'/.]
     * 13
     * 加入Shiro注解的使用，不加这个AOP注解不生效。
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Autowired SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
