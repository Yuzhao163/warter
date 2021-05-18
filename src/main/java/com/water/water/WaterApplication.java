package com.water.water;

import com.water.water.netty.MyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.water.water.util.PrintClassName;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootApplication
//public class WaterApplication extends WebMvcConfigurerAdapter {
@MapperScan("com.water.water.dao")
public class WaterApplication {

    public static void main(String[] args) {
//        SpringApplication.run(WaterApplication.class, args);
        ApplicationContext ctx = SpringApplication.run(WaterApplication.class
                , args);
        MyServer myServer = ctx.getBean("MyServer",MyServer.class);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(myServer);
        service.shutdown();
    }
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/*")
//                .allowCredentials(true)
//                .allowedHeaders("*")
//                .allowedOrigins("*")
//                .allowedMethods("*");
//    }
//    @EventListener(WebServerInitializedEvent.class)
//    public void onWebServerReady(WebServerInitializedEvent event){
//        PrintClassName printClassName = new PrintClassName();
//        System.out.println("当前webServer的实现类为："+ printClassName.PrintClassName(event)
//        );
//    }
}
