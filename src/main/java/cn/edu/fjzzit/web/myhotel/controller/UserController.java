package cn.edu.fjzzit.web.myhotel.controller;

import cn.edu.fjzzit.web.myhotel.config.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

//告诉SpringBoot这是一个Controller
@RestController
//添加访问路径前缀
@RequestMapping("/user")
//标识这个类是swagger的资源,设置标签属性
@Api(tags = "用户管理")

public class UserController {

    @PostMapping("/login")
    @ApiOperation("用户登陆")
    //name:参数名，value:参数描述，dataType：数据类型，required：是否为必须值，默认为false。
    //ApiImplicitParams:用在方法上，包含一组说明。
    //ApiImplicitParam：用注解来给方法入参添加说明。
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", required = true)
    })
    //测试，先用Get；后期应该用上方的PostMapping
    //@GetMapping("/login")
    public ResultJson login(String userName,String password){
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            String seessionId = subject.getSession().getId().toString();
            HashMap<String,String> map = new HashMap<>();
            map.put("token",seessionId);
            return new ResultJson("200","登陆成功",map);
        }catch (UnknownAccountException e){
            //账号不存在
            return new ResultJson("400","用户名或密码错误",null);
        }catch (DisabledAccountException e){
            return new ResultJson("400","该账号已被禁用",null);
        }catch (IncorrectCredentialsException e){
            return new ResultJson("400","用户名或密码错误",null);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultJson("400","未知错误，请联系管理员",null);
        }
    }

    @GetMapping("/need_login")
    public ResultJson needLogin(){
        return new ResultJson("400","请先登录！",null);
    }

    @GetMapping("/unauth")
    public ResultJson unauth(){
        return new ResultJson("400","您不具备相应权限！",null);
    }

    @GetMapping("/add_user")
    @ApiOperation("添加用户")
    @RequiresAuthentication
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权token",dataType = "String",required = true,paramType = "header")
    })
    public ResultJson addUser(){
        return new ResultJson("200","添加用户成功",null);
    }
}

