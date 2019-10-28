package cn.edu.fjzzit.web.myhotel.mapper;

import cn.edu.fjzzit.web.myhotel.model.UserInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Assert;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//让测试运行于Spring测试环境
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserInfoMapperTests {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testInsert(){
        String userName = "Robin";
        String salt = "abcd";
        UserInfo userInfo = new UserInfo();
         //加密
        SimpleHash simpleHash = new SimpleHash(Md5Hash.ALGORITHM_NAME,"123456",salt,3);
        userInfo.setUserName(userName);
        userInfo.setSalt(salt);
        userInfo.setPassword(simpleHash.toString());
        userInfo.setCreateTime(new Date());
        userInfo.setUserState(Byte.parseByte("1"));
        int i = userInfoMapper.insert(userInfo);
        //断言：影响行数为一行
        Assert.assertEquals(i,1);
    }

    @Test
    public void testFindFirstByUserName(){
        UserInfo userInfo = userInfoMapper.findFirstByUserName("");
        Assert.assertNull(userInfo);
    }
}
