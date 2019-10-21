package cn.zzb;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author Kyrie
 * @Date 2019/7/4
 */
@SpringBootApplication
@MapperScan("cn.zzb.mapper")
@ServletComponentScan
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("-服务启动-");
    }
}
