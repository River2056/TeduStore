import java.util.Calendar;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistsException;
import cn.tedu.store.service.ex.UsernameNotFoundException;

public class TestUserService {

	@Test
	public void testMapperInsert() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper userMapper = ctx.getBean("userMapper", UserMapper.class);
		
		// Dummy test data
		User user = new User();
		user.setUsername("Leon");
		user.setPassword("Kennedy");
		user.setPhone("0955123569");
		user.setEmail("firstdayonjob@RPD.com");
		user.setGender(1);
		user.setCreatedUser(user.getUsername());
		user.setCreatedTime(Calendar.getInstance().getTime());
		user.setModifiedUser(user.getUsername());
		user.setModifiedTime(Calendar.getInstance().getTime());
		
		userMapper.insert(user);
		Integer id = user.getId();
		System.out.println("資料輸入完成, 結果ID為: " + id);
		
		User u = userMapper.findUserByUsername(user.getUsername());
		System.out.println(user);
		
		ctx.close();
	}
	
	@Test
	public void testServiceRegister() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IUserService userService = ctx.getBean("userService", IUserService.class);
		
		User user = new User();
		user.setUsername("jQuery");
		user.setPassword("javascript");
		user.setPhone("0987552123");
		user.setEmail("jquery@js.com");
		user.setGender(0);
		user.setCreatedUser(user.getUsername());
		user.setCreatedTime(Calendar.getInstance().getTime());
		user.setModifiedUser(user.getUsername());
		user.setModifiedTime(Calendar.getInstance().getTime());
		
		try {
			Integer id = userService.register(user);
			System.out.println("註冊完成, 結果ID為: " + id);
			
		} catch (UsernameAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		
		
		User u = userService.findUserByUsername(user.getUsername());
		System.out.println("userService查找用戶: " + u);
		
		ctx.close();
	}
	
	@Test
	public void testServiceLogin() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IUserService userService = ctx.getBean("userService", IUserService.class);
		
		String username = "Leon";
		String password = "Kennedy";
		try {
			User user = userService.login(username, password);
			System.out.println("登入成功! 即將跳轉頁面...");
			System.out.println(user);
			
		} catch (UsernameNotFoundException e) {
			System.out.println(e.getMessage());
			
		} catch (PasswordNotMatchException e) {
			System.out.println(e.getMessage());
			
		}
		
		ctx.close();
	}
	
	@Test
	public void testServiceUpdate() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IUserService userService = ctx.getBean("userService", IUserService.class);
		
		Integer uid = 4;
		String oldPassword = "bootstrap1";
		String newPassword = "bootstrap4";
		
		try {
			Integer affectedRows = userService.changePassword(uid, oldPassword, newPassword);
			System.out.println("受影響的行數: " + affectedRows);
			System.out.println("修改完成! 修改結果為: " + userService.findUserById(uid).toString());
			
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			
		} catch (PasswordNotMatchException e) {
			System.out.println(e.getMessage());
			
		}
		
		ctx.close();
	}
	
	@Test
	public void testServiceEditProfile() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IUserService userService = ctx.getBean("userService", IUserService.class);
		
		try {
			Integer uid = 5;
			String username = "tungchinc";
			Integer gender = 1;
			String phone = "0987654123";
			String email = null;
			Integer affectedRows = userService.editProfile(uid, username, gender, phone, email);
			System.out.println("受影響的行數: " + affectedRows);
			
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			
		} catch (UsernameAlreadyExistsException e) {
			System.out.println(e.getMessage());
			
		}
		
		ctx.close();
	}
	
}
