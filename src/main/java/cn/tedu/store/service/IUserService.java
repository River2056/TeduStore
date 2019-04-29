package cn.tedu.store.service;

import cn.tedu.store.bean.User;

public interface IUserService {
	
	/**
	 * 根據用戶名查找用戶
	 * @param username 用戶名
	 * @return 返回用戶數據, 如果沒有, 則返回null
	 */
	User findUserByUsername(String username);
	
	/**
	 * 根據用戶ID查找用戶
	 * @param id 用戶ID
	 * @return 返回用戶數據, 如果沒有, 則返回null
	 */
	User findUserById(Integer id);
	
	/**
	 * 新用戶註冊
	 * @param user 新用戶數據
	 * @return 返回新用戶的ID
	 * @throws UsernameAlreadyExistsException 如果用戶名已經被占用, 則拋出此異常
	 */
	Integer register(User user);
	
	/**
	 * 用戶登入功能
	 * @param username 用戶名
	 * @param password 密碼
	 * @return 登入成功的用戶數據
	 * @throws UserNotFoundException 如果登入用戶名無法找到匹配數據, 則拋出此異常
	 * @throws PasswordNotMatchException 如果登入用戶輸入的密碼不對, 則拋出此異常
	 */
	User login(String username, String password);
	
	/**
	 * 用戶修改密碼
	 * @param uid 被修改的用戶ID(必須)
	 * @param oldPassword 原密碼
	 * @param newPassword 新密碼
	 * @return 返回受影響的行數
	 * @throws PasswordNotMatchException 原密碼不正確
	 * @throws UserNotFoundException 用戶信息不存在
	 */
	Integer changePassword(Integer uid, String oldPassword, String newPassword);
	
	/**
	 * 修改用戶基本資料
	 * @param uid 被修改的用戶ID
	 * @param username 新用戶名
	 * @param gender 新性別
	 * @param phone 新手機號碼
	 * @param email 新電子信箱
	 * @return 受影響的行數
	 * @throws UserNotFoundException 用戶信息不存在
	 * @throws UsernameAlreadyExistsException 用戶名已經被占用
	 */
	Integer editProfile (Integer uid, String username, Integer gender, String phone, String email);
	
	/**
	 * 根據用戶名檢察該用戶名是否存在
	 * @param username 用戶輸入的用戶名
	 * @return 如果有匹配數據, 返回true, 否則返回false
	 */
	boolean checkUsernameExists(String username);
	
	/**
	 * 根據電話檢查該電話是否已經存在
	 * @param phone 用戶輸入的電話
	 * @return 如果有匹配數據, 返回true, 否則返回false
	 */
	boolean checkPhoneExists(String phone);
	
	/**
	 * 根據電子信箱檢查該信箱是否已經存在
	 * @param email 用戶輸入的電子信箱
	 * @return 如果有匹配數據, 返回true, 否則返回false
	 */
	boolean checkEmailExists(String email);
	
}
