package cn.edu.fjzzit.web.myhotel.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 	//使用Lombok自动生成setter,getter
@NoArgsConstructor //使用Lombok自动生成无参的构造方法
@AllArgsConstructor //使用Lombok自动生成全参的构造方法

public class ResultJson {
    private String code;
    private String msg;
    private Object data;
}

