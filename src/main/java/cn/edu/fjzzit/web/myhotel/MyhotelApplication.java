package cn.edu.fjzzit.web.myhotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MyhotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyhotelApplication.class, args);
    }

}
