package cn.edu.fjzzit.web.myhotel.service;

import cn.edu.fjzzit.web.myhotel.model.UserInfo;


public interface UserService {
    //根据用户名返回信息。
    //@param userName
    //@return 返回用户名对应的用户信息，如果找不到，返回null。
    //获取用户信息
    UserInfo getUserInfo(String userName);

    //获取用户角色
    //Set<String> getUserRoles(Integer userID);

}
