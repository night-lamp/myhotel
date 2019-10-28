package cn.edu.fjzzit.web.myhotel.mapper;

import cn.edu.fjzzit.web.myhotel.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {
    int insert(UserInfo record);
    //通过用户ID查询用户信息
    UserInfo findFirstByUserName(@Param("userName")String userName);
}