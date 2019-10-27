package cn.edu.fjzzit.web.myhotel.controller;

import cn.edu.fjzzit.web.myhotel.config.ResultJson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//告诉SpringBoot这是一个Controller
@RestController
//添加访问路径前缀
@RequestMapping("/user")

public class UserController {

    //name:参数名，value:参数描述，dataType：数据类型，required：是否为必须值，默认为false。
    //ApiImplicitParams:用在方法上，包含一组说明。
    //ApiImplicitParam：用注解来给方法入参添加说明。
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", required = true)
    })
    //测试，先用Get；后期应该用上方的PostMapping
    //@GetMapping("/login")
    @PostMapping("/login")
    public ResultJson login(){
        return new ResultJson("200","成功","");
    }
}

