package cn.edu.fjzzit.web.myhotel.mapper;

import cn.edu.fjzzit.web.myhotel.model.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleInfoMapper {
    int insert(RoleInfo record);
    List<String> queryRoleNameByUserId(@Param("userId") Integer userId);
}