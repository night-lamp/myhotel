package cn.edu.fjzzit.web.myhotel.Controller;

import cn.edu.fjzzit.web.myhotel.config.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//告诉SpringBoot这是一个Controller
@RestController
//添加访问路径前缀
@RequestMapping("/user")

public class UserController {
    //@PostMapping("/login")
    //测试，先用Get；后期应该用上方的PostMapping
    @GetMapping("/login")
    public ResultJson login(){
        return new ResultJson("200","成功","");
    }
}

