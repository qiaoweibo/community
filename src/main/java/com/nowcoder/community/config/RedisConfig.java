package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * 用注解@Configuration声明RedisConfig为一个配置类
 * @author QiaoWeiBo
 * @date 2022/6/15 6:11 PM
 */
@Configuration
public class RedisConfig {

    /**
     * 注解@Bean在这为了定义第三方的bean
     * 把那个对象装配到容器中，就返回那个对象{RedisTemplate}(方法名就是bean的名字)
     * Template要想具备访问数据库的能力，需要创建连接，而连接又是由连接工厂{RedisConnectionFactory}创建
     * (所以需要把连接工厂注入给Template)
     * ⚠️：当你定义一个bean的时候，方法上声明了RedisConnectionFactory参数，
     *    spring容器会自动的把RedisConnectionFactory对应的bean注入进来(该bean已经被容器实例化)
     *
     *
     * @author QiaoWeiBo
     * @date 2022/6/15 1:49 PM
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        //实例化
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //设置工厂
        template.setConnectionFactory(factory);

        //设置key的序列化方式
        template.setKeySerializer(RedisSerializer.string());
        //设置value的序列户的方式
        template.setValueSerializer(RedisSerializer.json());
        //设置hash的key的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        //设置hash的value的序列化方式
        template.setHashValueSerializer(RedisSerializer.json());

        //为了让template中所设置的参数生效，还需要触发生效
        template.afterPropertiesSet();

        return template;
    }

}
