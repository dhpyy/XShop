package cn.swust.xshop.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.swust.xshop.category.service.CategoryService;
import cn.swust.xshop.category.vo.Category;

public class ServiceTest {
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	@Test
	public void testBaseService() throws Exception {
		CategoryService categoryService = (CategoryService) ac.getBean("categoryService");
		Category category = new Category();
		categoryService.save(category);
	}
}
