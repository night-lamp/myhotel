package cn.edu.fjzzit.web.myhotel.service.impl;

import cn.edu.fjzzit.web.myhotel.mapper.UserInfoMapper;
import cn.edu.fjzzit.web.myhotel.model.UserInfo;
import cn.edu.fjzzit.web.myhotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfo(String userName) {
        return userInfoMapper.findFirstByUserName(userName);
    }


}
