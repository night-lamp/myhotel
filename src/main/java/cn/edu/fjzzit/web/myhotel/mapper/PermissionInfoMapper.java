package cn.edu.fjzzit.web.myhotel.mapper;

import cn.edu.fjzzit.web.myhotel.model.PermissionInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionInfoMapper {
    int insert(PermissionInfo record);
}