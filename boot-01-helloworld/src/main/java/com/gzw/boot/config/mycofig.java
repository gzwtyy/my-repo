package com.gzw.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.gzw.boot.beans.Car;
import com.gzw.boot.beans.Pet;
import com.gzw.boot.beans.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

//1，配置类中使用@bean标注在方法上，给容器中注册组件，默认是单实例的
//2，配置类本身也是组件
//3,proxyBeanMethods :代理bean的方法
//full(proxyBeanMethods = true)
//lite(proxyBeanMethods = false)
//解决组件依赖

//@Import({User.class, DBHelper.class})
//给容器中自动创建这两个类型的组件，组件名称为全类名

@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true) //告诉springboot这是一个配置类
//@ConditionalOnBean(name = "cat")
@ConditionalOnMissingBean(name = "gzw")
@ImportResource("classpath:beans.xml")

//1，开启car配置绑定功能
//2，将car这个组件自动注册到容器中
@EnableConfigurationProperties(Car.class)
public class mycofig {



    @Bean
    public Pet cat(){
        return new Pet("xixi");
    }


    @Bean("dog")
    public Pet dog(){
        return new Pet("wangcai");
    }

    //外部无论对配置类中的这个组件的注册方法（user01）调用多少次，获取的都是之前注册容器中的单实例
    @ConditionalOnBean(name="dog")//如果存在dog组件才会添加接下来的组件
    @Bean("gzw") //容器中添加组件，方法名作为组件id，返回类型就是组件类型
    public User user01(){
        User zhangsan = new User("dfdf", 12);
        zhangsan.setPet(cat());
        return  zhangsan;
    }



}
