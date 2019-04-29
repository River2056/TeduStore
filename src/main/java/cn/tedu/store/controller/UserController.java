package cn.tedu.store.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistsException;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Resource(name="userService")
	private IUserService userService;

	@RequestMapping("/register.do")
	public String showRegister() {
		return "register";
	}
	
	@RequestMapping("/login.do")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/password.do")
	public String showPassword() {
		return "user_password";
	}
	
	@RequestMapping("/profile.do")
	public String showProfile(ModelMap modelMap, HttpSession session) {
		// 查詢當前登錄的用戶數據
		Integer uid = getUidFromSession(session);
		User user = userService.findUserById(uid);
		// 將用戶數據轉發到前端頁面
		modelMap.addAttribute("user", user);
		
		// 執行轉發
		return "user_profile";
	}
	
	/**
	 * 註冊頁面  - 檢測用戶名
	 * @param username
	 * @return
	 */
	@RequestMapping("/check_username.do")
	@ResponseBody
	public ResponseResult<Void> checkUsername(String username) {
		ResponseResult<Void> rr;
		// 檢查用戶名是否存在
		if(userService.checkUsernameExists(username)) {
			// 結果為true, 表示用戶名已經存在
			rr = new ResponseResult<Void>(0, "用戶名已經存在!");
			
		} else {
			// 結果為false, 表示用戶名尚未被註冊
			rr = new ResponseResult<Void>(1, "用戶名可以使用");
			
		}
		return rr;
	}
	
	/**
	 * 註冊頁面 - 檢測手機
	 * @param phone
	 * @return
	 */
	@RequestMapping("/check_phone.do")
	@ResponseBody
	public ResponseResult<Void> checkPhone(String phone) {
		ResponseResult<Void> rr;
		if(userService.checkPhoneExists(phone)) {
			rr = new ResponseResult<Void>(0, "手機號碼已註冊!");
			
		} else {
			rr = new ResponseResult<Void>(1, "手機號碼可以使用");
			
		}
		return rr;
	}
	
	/**
	 * 註冊頁面 - 檢測電子信箱
	 * @param email
	 * @return
	 */
	@RequestMapping("/check_email.do")
	@ResponseBody
	public ResponseResult<Void> checkEmail(String email) {
		// 聲明返回值
		ResponseResult<Void> rr;
		// 檢查該電子信箱是否存在
		if(userService.checkEmailExists(email)) {
			rr = new ResponseResult<Void>(0, "電子信箱已被占用!");
			
		} else {
			rr = new ResponseResult<Void>(1, "電子信箱可以使用");
			
		}
		// 返回
		return rr;
	}
	
	/**
	 * 註冊
	 * @param username
	 * @param password
	 * @param phone
	 * @param email
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/handle_register.do")
	@ResponseBody
	public ResponseResult<Void> handleRegister(
			@RequestParam("uname") String username, 
			@RequestParam("upwd") String password, 
			@RequestParam("phone") String phone, 
			@RequestParam("email") String email) {
		// 聲明返回值
		ResponseResult<Void> rr;
		
		// 封裝數據
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		
		//執行註冊 
		try {
			Integer id = userService.register(user);
			System.out.println("註冊成功! 新用戶ID: " + id);
			rr = new ResponseResult<Void>(1, "註冊成功!");
			
		} catch (UsernameAlreadyExistsException e) {
			System.out.println(e.getMessage());
			rr = new ResponseResult<Void>(0, e);
			
		}
		
		// 返回
		return rr;
		
	}
	
	/**
	 * 登入
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/handle_login.do")
	@ResponseBody
	public ResponseResult<Void> handleLogin(
			@RequestParam("username") String username, 
			@RequestParam("password") String password,
			HttpSession session) {
		ResponseResult<Void> rr;
		
		try {
			User user = userService.login(username, password);
			rr = new ResponseResult<Void>(1);
			session.setAttribute("uid", user.getId());
			session.setAttribute("username", user.getUsername());
			
		} catch (UserNotFoundException e) {
			rr = new ResponseResult<Void>(0, e);
			
		} catch (PasswordNotMatchException e) {
			rr = new ResponseResult<Void>(-1, e);
			
		}
		
		return rr;
	}
	
	/**
	 * 用戶修改密碼功能
	 * @param oldPassword
	 * @param newPassword
	 * @param session
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/handle_change_password.do")
	@ResponseBody
	public ResponseResult<Void> handleChangePassword(
			@RequestParam("old_password") String oldPassword, 
			@RequestParam("new_password") String newPassword, 
			HttpSession session) {
		// 從session獲取用戶ID
		Integer uid = getUidFromSession(session);
		
		// 聲明ResponseResult對象, 用於封裝數據
		ResponseResult<Void> rr;
		
		try {
			Integer affectedRows = userService.changePassword(uid, oldPassword, newPassword);
			rr = new ResponseResult<Void>(1, "修改密碼成功!");
			
		} catch (UserNotFoundException e) {
			// 修改密碼失敗, 用戶不存在
			System.out.println(e.getMessage());
			rr = new ResponseResult<Void>(0, e);
			
		} catch (PasswordNotMatchException e) {
			// 修改密碼失敗, 原密碼不正確
			System.out.println(e.getMessage());
			rr = new ResponseResult<Void>(-1, e);
		}
		
		return rr;
	}
	
	/**
	 * 用戶修改個人資料功能
	 * @param username
	 * @param gender
	 * @param phone
	 * @param email
	 * @param session
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/handle_edit_profile.do")
	@ResponseBody
	public ResponseResult<Void> handleEditProfile(
			String username, 
			Integer gender, 
			String phone, 
			String email, 
			HttpSession session) {
		Integer uid = getUidFromSession(session);
		ResponseResult<Void> rr;
		
		try {
			Integer affectedRows = userService.editProfile(uid, username, gender, phone, email);
			rr = new ResponseResult<Void>(1, "修改成功!");
			// 更新Session中的username
			User u = userService.findUserById(uid);
			session.setAttribute("username", u.getUsername());
			
		} catch (UserNotFoundException e) {
			rr = new ResponseResult<Void>(-1, e);
			
		} catch (UsernameAlreadyExistsException e) {
			rr = new ResponseResult<Void>(-2, e);
		}
		
		return rr;
	}
	
	/**
	 * 登出
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String handleLogout(HttpSession session, HttpServletRequest request) {
		// 清除Session中的所有數據
		session.invalidate();
		// 重定向目標
		String url = "../main/index.do";
		// 執行重定向
		return "redirect:" + url;
	}
	
	
}
