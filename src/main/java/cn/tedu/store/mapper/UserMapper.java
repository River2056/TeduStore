package cn.tedu.store.mapper;

import cn.tedu.store.bean.User;

public interface UserMapper {

	/**
	 * 新用戶註冊
	 * @param user 新用戶數據
	 * @return 返回新用戶的ID
	 */
	void insert(User user);
	
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
	 * 根據用戶電話檢查個數
	 * @param phone 用戶電話
	 * @return 返回個數0或1
	 */
	Integer getRecordCountByPhone(String phone);
	
	/**
	 * 獲取電子信箱對應的數據數量
	 * @param email 電子信箱名稱
	 * @return 電子信箱對應的數據數量
	 */
	Integer getRecordCountByEmail(String email);
	
	/**
	 * 修改用戶數據, 可用於修改個人資料, 也可用於修改密碼
	 * @param user 必須包含被修改的用戶ID, 及需要修改的新數據
	 * @return 受影響行數, 由於是基於ID篩選後的修改, 最終表現為0或1
	 */
	Integer update(User user);
	
}
