package cn.edu.fjzzit.web.myhotel.config;

import cn.edu.fjzzit.web.myhotel.model.UserInfo;
import cn.edu.fjzzit.web.myhotel.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
        import org.apache.shiro.realm.AuthorizingRealm;
        import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权（权限认证）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证（身份认证）
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken authToken = (UsernamePasswordToken) token;
        String userName = authToken.getUsername();
        UserInfo userInfo = userService.getUserInfo(userName);
        if(userInfo == null){
            //账号不存在，抛出异常
            throw new UnknownAccountException();
        }else if(userInfo.getUserState() == 2){
            //账号被禁用，抛出异常
            throw new DisabledAccountException();
        }else{
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    userInfo,
                    userInfo.getPassword(),
                    ByteSource.Util.bytes(userInfo.getSalt()),
                    getName());
            return authenticationInfo;
        }
    }
}
