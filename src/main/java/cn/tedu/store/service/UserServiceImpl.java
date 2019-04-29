package cn.tedu.store.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistsException;
import cn.tedu.store.service.ex.UsernameNotFoundException;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Resource(name="userMapper")
	private UserMapper userMapper;

	public User findUserByUsername(String username) {
		return userMapper.findUserByUsername(username);
	}
	
	public User findUserById(Integer id) {
		return userMapper.findUserById(id);
	}
	
	public Integer register(User user) {
		if(!checkUsernameExists(user.getUsername())) {
			// means no such user, allow register, return new user ID
			userMapper.insert(user);
			return user.getId();
			
		} else {
			// user already exists, throw exception
			throw new UsernameAlreadyExistsException("用戶名已經被占用!");
			
		}
		
	}
	
	public User login(String username, String password) {
		User user = findUserByUsername(username);
		if(user != null) {
			// true -> user exists, check password
			if(user.getPassword().equals(password)) {
				// username, password matched, proceed login;
				return user;
			} else {
				// password not match, throw exception
				throw new PasswordNotMatchException("密碼不正確! 請重新檢查!");
			}
			
		} else {
			// false -> user not found, throw exception
			throw new UsernameNotFoundException("用戶名不存在! 請先註冊!");
		}
		
	}
	
	public Integer changePassword(Integer uid, String oldPassword, String newPassword) {
		// 先根據id找用戶
		// 未找到 -> 拋出異常UserNotFoundException
		// 找到 -> 如下
		// 比對原密碼是否相符
		// 不相符 -> 拋出異常PasswordNotMatchException
		// 相符 -> 如下
		// 創建User, 執行userMapper.update(user);
		User user = findUserById(uid);
		if(user == null) {
			throw new UserNotFoundException("該用戶不存在, 可能已經被刪除! 請聯繫管理員!");

		} else {
			// user exists -> check password
			if(user.getPassword().equals(oldPassword)) {
				// oldPassword match, proceed to change newPassword
				// create User entity
				User newUser = new User();
				newUser.setId(uid);
				newUser.setPassword(newPassword);
				return userMapper.update(newUser);
			} else {
				// oldPassword failed, throw exception
				throw new PasswordNotMatchException("用戶原密碼不正確! 請重新檢查!");
			}
		}
		
	}
	
	public Integer editProfile(Integer uid, String username, Integer gender, String phone, String email) {
		User originalUser = findUserById(uid);
		User newUser = new User();
		
		if(originalUser == null) {
			throw new UserNotFoundException("該用戶不存在, 可能已經被刪除, 請聯繫系統管理員");
			
		}
		// originalUser exists, continue with edit profile
		// check new username
		if(originalUser.getUsername().equals(username)) {
			// user didn't edit name, skip setUsername
			
		} else {
			// user change his name, check if repeat
			User editedUser = findUserByUsername(username);
			if(editedUser == null) {
				// means user can change his name
				newUser.setUsername(username);
				
			} else {
				// means user change to a taken name, BLOCK
				throw new UsernameAlreadyExistsException("該用戶名已經存在!請重新取名!");
				
			}
			
		}
		newUser.setId(uid);
		newUser.setGender(gender);
		if(phone != null && phone.length() >= 10) {
			newUser.setPhone(phone);
		}
		if(email != null && !"".equals(email)) {
			newUser.setEmail(email);
		}
		newUser.setModifiedUser("[System Admin]");
		newUser.setModifiedTime(new Date());
		
		return userMapper.update(newUser);
	}

	public boolean checkUsernameExists(String username) {
		return findUserByUsername(username) != null;
	}
	
	public boolean checkPhoneExists(String phone) {
		return userMapper.getRecordCountByPhone(phone) > 0;
	}
	
	public boolean checkEmailExists(String email) {
		return userMapper.getRecordCountByEmail(email) > 0;
	}

	

	
	
	
}
