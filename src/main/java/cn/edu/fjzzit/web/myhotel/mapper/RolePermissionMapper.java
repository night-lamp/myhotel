package cn.edu.fjzzit.web.myhotel.mapper;

import cn.edu.fjzzit.web.myhotel.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolePermissionMapper {
    int insert(RolePermission record);
}