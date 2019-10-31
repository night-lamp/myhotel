package cn.edu.fjzzit.web.myhotel.service.impl;

import cn.edu.fjzzit.web.myhotel.mapper.RoleInfoMapper;
import cn.edu.fjzzit.web.myhotel.mapper.UserInfoMapper;
import cn.edu.fjzzit.web.myhotel.mapper.UserRoleMapper;
import cn.edu.fjzzit.web.myhotel.model.UserInfo;
import cn.edu.fjzzit.web.myhotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required=false)
    private UserInfoMapper userInfoMapper;
    @Autowired(required=false)
    private RoleInfoMapper roleInfoMapper;

    @Override
    public UserInfo getUserInfo(String userName) {
        return userInfoMapper.findFirstByUserName(userName);
    }

    @Override
    public Set<String> getUserRoles(Integer userId) {
        List<String> roleNameList = roleInfoMapper.queryRoleNameByUserId(userId);
        Set<String> roles = new HashSet<>(roleNameList);
        return roles;

    }


}
