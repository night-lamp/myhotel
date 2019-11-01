package cn.edu.fjzzit.web.myhotel.mapper;

import cn.edu.fjzzit.web.myhotel.model.UserInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Assert;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

//让测试运行于Spring测试环境
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserInfoMapperTests {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    @Rollback(value = false)
    public void testInsert(){
        String userName = "admin";
        //使用UUID进行密码的加密。
        String salt = UUID.randomUUID().toString().replace("-","");
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

        String userName = "admin_1";
        String salt = "adcb";
        UserInfo userInfo1 = new UserInfo();
        //加密
        SimpleHash simpleHash = new SimpleHash(Md5Hash.ALGORITHM_NAME,"123456",salt,3);
        userInfo1.setUserId(1);
        userInfo1.setUserName(userName);
        userInfo1.setSalt(salt);
        userInfo1.setPassword(simpleHash.toString());
        userInfo1.setUserState(Byte.parseByte("1"));

        userInfo = userInfoMapper.findFirstByUserName("admin");
        Assert.assertNotNull(userInfo);
        //Assert.assertEquals(userInfo,userInfo1);

    }
}
