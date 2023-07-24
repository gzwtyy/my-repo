package com.gzw.boot;

import com.gzw.boot.beans.Pet;
import com.gzw.boot.beans.User;
import com.gzw.boot.config.mycofig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//注解告诉springboot这是一个springboot应用
@SpringBootApplication(scanBasePackages="com.gzw")
/*@SpringBootApplication注解相当于
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(scanBasePackages="com.gzw")指定需要扫描哪些包*/
public class MainApplication {
    public static void main(String[] args) {
        //1,返回ioc容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2,查看容器中的组件
        String[] names = run.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }

        //3,容器中获取组件,这里的组件是指ioc容器中实例对象
        //组件就是类，这里是javabean（pojo,domain）对象，叫法不同
        Object gzw1 = run.getBean("gzw");
        Object gzw2 = run.getBean("gzw");
        System.out.println(gzw1==gzw2);
        mycofig bean = run.getBean(mycofig.class);
        System.out.println(bean);

        //4,如果@Configuration(proxyBeanMethods = true)代理对象调用方法，
        //springboot会检查组件是否在容器中，为了保持组件单实例
        User user01 = bean.user01();
        User user02 = bean.user01();
        System.out.println(user01==user02);

        User gzw = run.getBean("gzw", User.class);
        String name = gzw.getName();
        System.out.println(name);
        Pet cat = run.getBean("cat", Pet.class);
        System.out.println(gzw.getPet()==cat);

        //查看容器中是否存在组件
        boolean dog = run.containsBean("dog");
        boolean gzw21 = run.containsBean("gzw2");
        System.out.println("gzw2是否存在:"+gzw21);
        System.out.println(dog);


    }
}
