package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Address;

public interface IAddressService {

	/**
	 * 增加新地址
	 * @param address 地址實體類
	 * @return 返回新地址的ID
	 */
	Integer add(Address address);
	
	/**
	 * 根據登錄用戶的uid查詢這個用戶的所有收貨人地址
	 * @param uid 當前登錄用戶的ID
	 * @return 返回這個用戶的所有收貨人地址, 如果沒有數據, 則返回長度為0的集合, 不會返回null
	 */
	List<Address> getAddressListByUid(Integer uid);
	
	/**
	 * 根據ID與當前用戶ID查找一條地址訊息
	 * @param id 被搜尋的數據ID
	 * @param uid 當前登錄的用戶ID
	 * @return 返回被封裝的地址實體類
	 */
	Address getAddressByIdAndUid(Integer id, Integer uid);
	
	/**
	 * 刪除地址功能
	 * 根據地址ID跟當前登錄用戶ID做刪除操作
	 * @param id 被刪除的數據ID
	 * @param uid 當前登錄的用戶
	 * @return 返回受影響的行數, 1代表成功刪除, 其他則表示刪除失敗
	 * @throws DataNotFoundException 當數據不存在時, 將拋出此異常
	 */
	Integer delete(Integer id, Integer uid);
	
	/**
	 * 修改收貨地址
	 * @param address 只少包括被修改的數據ID, 所歸屬的用戶ID, 和新數據
	 * @return 受影響的行數, 即返回0或1
	 */
	Integer update(Address address);
	
	/**
	 * 將某一條地址設為默認
	 * @param uid 當前登錄用戶所屬ID
	 * @param id 被設置為默認的地址ID
	 * @return 如果設置成功, 將返回1, 否則返回0, 失敗原因可能因為數據已經被刪除, 或uid過期
	 * @throws DataNotFoundException 
	 */
	Integer setDefault(Integer uid, Integer id);
	
}
