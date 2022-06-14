package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * 普通的配置类用注解@Configuration，程序的入口用@SpringBootApplication配置类注解「一般情况」
 * 注解@Configuration表示该类是一个配置类，不是普通的类
 * @author QiaoWeiBo
 * @date 2022/6/14 4:32 PM
 */
@Configuration
public class AlphaConfig {

    /**
     * 定义第三方的bean需要加上注解@Bean(在方法之前)
     * 下面这个日期格式基本上是通用的，所以把它装配到容器里，实例化一次，就可以通用了
     * 这段public SimpleDateFormat simpleDateFormat() 代码的含义就是：这个方法simpleDateFormat的
     * 返回对象SimpleDateFormat将会被装配到容器中，bean的名字就是simpleDateFormat
     * (bean的名字是以方法命名的)
     * @author QiaoWeiBo
     * @date 2022/6/14 4:34 PM
     */
    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

}
