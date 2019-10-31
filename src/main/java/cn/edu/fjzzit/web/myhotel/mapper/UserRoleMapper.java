package cn.edu.fjzzit.web.myhotel.mapper;

import cn.edu.fjzzit.web.myhotel.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    int insert(UserRole record);

    List<String> findUserRolesByUserID(Integer userID);
    //通过用户ID查询用户角色

}