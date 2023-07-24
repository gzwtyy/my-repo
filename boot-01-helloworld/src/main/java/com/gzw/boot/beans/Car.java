package com.gzw.boot.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*只有在容器中的组件，才有springboot提供的功能
* */
//@Component
@ToString
@Data//对应getter和setter
@AllArgsConstructor//有参
@NoArgsConstructor//无参
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;

}
