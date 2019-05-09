import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.controller.UserController;

public class TestCaptcha {

	@Test
	public void testUserControllerCaptcha() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mvc.xml", "spring-dao.xml", "spring-service.xml");
		UserController userController = ctx.getBean("userController", UserController.class);
		
		String randomCode = userController.createCode(5);
		System.out.println(randomCode);
		
		ctx.close();
	}
}
