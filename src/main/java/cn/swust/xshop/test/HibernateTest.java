package cn.swust.xshop.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class HibernateTest {
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");  // Spring容器对象在监听器中创建
    																					   // 运行JUnit不经过监听器，是单独的一个环境 需要手动创建 Spring容器对象
	// 测试Spring-Hibernate:SessionFactory
	@Test													// Spring容器对象在监听器中创建,该测试代码必须在tomcat环境中运行
	public void testSessionFactory() throws Exception {
	SessionFactory sessionFactory = (SessionFactory)ac.getBean("sessionFactory");
	System.out.println("---------" + sessionFactory);
	}
}