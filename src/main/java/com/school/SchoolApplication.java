package com.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ServletComponentScan
@SpringBootApplication
@EnableCaching
public class SchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);

//        Super sooper = new Super();
//        Sub sub = new Sub();
//        System.out.println(sooper.getLenght().toString() + "," + sub.getLenght().toString() );

    }
}

//class Super {
//    public Integer getLenght() { return new Integer(4); }
//}
//
//class Sub extends Super {
//
//    public Integer getLenght() {
//        return new Integer(5);
//    }
//
//}