package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注解@SpringBootApplication所标识的类,就表示这个类是一个配置文件
 * 该注解的底层有：
 * 注解@SpringBootConfiguration，就可以表示这个类是一个配置文件
 * 注解@EnableAutoConfiguration表示要启动自动配置「这就是为啥我们基本不用做什么配置，也能把这个服务启动起来」
 * 注解@ComponentScan表示组件扫描，他会自动的扫描某些包下的某些bean，装配到容器里。如何起作用？：
 * 		1、它会扫描配置类所在的包以及子包下bean(所在的包：com.nowcoder.community，以及子包：controller)
 * 		2、需要有像@Controller的注解 加到bean上 才会被扫描(这样的注解有四个：Service、Repository、Controller、Component)
 *  	Service注解：用于标明业务组件
 *  	Controller注解：用于标识处理请求的组件
 *  	Repository注解：用于数据库访问的组件
 *  	Component注解：通用，任何类都可以用
 *  	「注解Service、Repository、Controller由注解@Component实现」
 *
 *  (一般程序的入口用@SpringBootApplication配置类注解)，其他的配置类不用这个注解(一般)
 * @author QiaoWeiBo
 * @date 2022/6/14 12:20 AM
 */

@SpringBootApplication
public class CommunityApplication {
	/**
	 * (正式运行程序是以配置类CommunityApplication来运行)
	 * 运行下面的main中的SpringApplication.run()方法，底层做了两件事：
	 * 1、底层自动启动tomcat服务器，
	 * （springboot中的jar中带有tomcat，换言之就是sptingboot以jar包的形式，内嵌了一个tomcat）
	 * 2、自动帮我们创建了spring容器「就是说在web项目中，spring容器不需要我们主动创建，它是自动被创建，在这个方法的底层」
	 * （spring容器被创建以后，它会自动的去扫描某些包下的某些bean，将这些bean去装配到容器里）
	 * 那些bean会被扫描到需要看  类CommunityApplication「这个类是run()方法的参数」
	 * （SpringApplication在启动的时候是需要配置的，类CommunityApplication就是一个配置文件）
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
