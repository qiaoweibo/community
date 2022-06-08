package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunityApplication {
	/**
	 * 运行下面的main，底层自动启动tomcat服务器，
	 * （springboot中的jar中带有tomcat，换言之就是sptingboot以jar包的形式，内嵌了一个tomcat）
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
