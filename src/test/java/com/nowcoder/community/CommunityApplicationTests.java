package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 注解@ContextConfiguration(classes = CommunityApplication.class)表示：
 * 在测试代码中启用类CommunityApplication作为配置类（测试代码也希望和正式环境所用的配置类一样）
 *
 * 那个类想获得IoC容器就需要
 * 1、实现 ApplicationContextAware 接口
 * 2、将spring容器引用一下，暂存一下（用私有变量applicationContext记录「就相当于把容器传进来」）
 *
 * @author QiaoWeiBo
 * @date 2022/6/14 9:48 AM
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	/**
	 * 参数applicationContext其实就是一个spring容器，ApplicationContext是一个接口类型
	 * 「ApplicationContext继承HierarchicalBeanFactory接口，HierarchicalBeanFactory接口又继承BeanFactory」
	 * （BeanFactory接口就是spring容器的顶层接口）
	 * @author QiaoWeiBo
	 * @date 2022/6/14 10:11 AM
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * applicationContext就是spring容器
	 * applicationContext.getBean(AlphaDao.class)就是从容器中调用这个bean(AlphaDao)
	 * 「getBean就是按照类型去获取bean，上面的AlphaDao是接口」
	 * 这么做的好处？
	 * 	demo:现在构造两个bean：alphaDaoHibernateImpl和alphaDaoMyBatisImpl，
	 * 	想用那个就加上注解@Primary
	 * 	(这么做：解耦)
	 * 	调用的地方依赖的是接口「此处代码」，所以该接口的实现类变了，这里(getBean(AlphaDao.class))是不用动的
	 * 	「调用方和实现类不直接发生关系」
	 * 	新的问题：若程序的某一块想用Hibernate，其他想用MyBatis怎么办？通过bean的名字去获取bean
	 * 	「bean的默认名字是：类名首字母小写」，如何自定义bean的名字：注解("xxx")
	 *
	 * @author QiaoWeiBo
	 * @date 2022/6/14 11:51 AM
	 */

	@Test
	public void testApplicationContext() {
		System.out.println(applicationContext);

		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);//按照类型去获取bean
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("alphaDaoHibernate", AlphaDao.class);//按照bean的名字去获取bean（这里默认返回的是Object类型，所以加上, AlphaDao.class）
		System.out.println(alphaDao.select());
	}

	/**
	 * 从下面的测试结果可以看出：bean只被实例化一次，初始化和销毁也是一次，说明容器中就是一个bean
	 * 被spring容器所管理的bean默认是单例模式(singleton)
	 * 如果不想设置单例模式怎么办？(在目标bean上加上注解Scope())「prototype表示多实例」
	 * (项目中一般还是默认单例)
	 * @author QiaoWeiBo
	 * @date 2022/6/14 4:06 PM
	 */
	@Test
	public void testBeanManager() {
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat =
				applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	/**
	 * 下面是获取bean的另一种方式(依赖注入)，一般都用这种
	 * 想要获取alphaDao接口的Hibernate实现类可以加上注解@Qualifier("alphaDaoHibernate")
	 *
	 * (注解@Autowired也可以加在类的构造器前面，通过构造器注入；也可以加在set方法之前，通过set方法注入)
	 * (一般是直接加在属性之前，直接注入给属性，更方便)
	 * @author QiaoWeiBo
	 * @date 2022/6/14 5:47 PM
	 */
	@Autowired
	@Qualifier("alphaDaoHibernate")
	private AlphaDao alphaDao;

	@Autowired
	private AlphaService alphaService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDependencyInjection() {
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}

}
