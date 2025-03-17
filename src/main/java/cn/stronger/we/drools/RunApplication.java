package cn.stronger.we.drools;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class RunApplication.class
 * @department Platform R&D
 * @date 2025/3/14
 * @desc 项目启动类
 */
@Slf4j
@MapperScan("cn.stronger.we.drools.gateway.mapper")
@EnableFeignClients(basePackages = "cn.stronger.we.leaf.client")
@SpringBootApplication
public class RunApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RunApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        String port = environment.getProperty("server.port");
        String application = environment.getProperty("spring.application.name");
        String mysqlUrl = environment.getProperty("spring.datasource.url");
        String redisUrl = environment.getProperty("spring.redis.host") + ":" + environment.getProperty("spring.redis.port");
        System.out.println("------------------------------------------------------------");
        System.out.println("      [" + application + "]服务启动成功|端口号：" + port);
        System.out.println("      [MySql]连接：" + mysqlUrl);
        System.out.println("      [Redis]连接：" + redisUrl);
        System.out.println("------------------------------------------------------------");
    }
}
