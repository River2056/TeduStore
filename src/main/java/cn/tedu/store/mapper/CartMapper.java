package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Cart;

public interface CartMapper {

	/**
	 * 將商品新增至購物車
	 * @param cart 購物車信息
	 * @return 受影響的行數
	 */
	Integer add(Cart cart);
	
	/**
	 * 刪除購物車商品
	 * @param uid 用戶ID
	 * @param id 商品ID
	 * @return 返回受影響的行數
	 */
	Integer delete(
			@Param("uid") Integer uid, 
			@Param("id") Integer id);
	
	/**
	 * 獲取用戶的購物車中的商品列表
	 * @param uid 用戶ID
	 * @return 返回用戶購物車中的商品列表List, 沒有則返回長度為0的List
	 */
	List<Cart> getCartList(Integer uid);
	
	/**
	 * 根據商品ID獲取購物車中的商品訊息
	 * @param uid 用戶ID
	 * @param id 購物車中的數據ID
	 * @return 獲取到的訊息
	 */
	Cart getCartById(
			@Param("uid") Integer uid, 
			@Param("id") Integer id);
	
	/**
	 * 獲取紀錄的數量
	 * @param uid 用戶ID
	 * @param goodsId 商品ID
	 * @return 紀錄的數量, 由於業務層應該維護對應的業務邏輯, 返回值應該是0或1
	 */
	Integer getRecordCount(
			@Param("uid") Integer uid, 
			@Param("goodsId") Integer goodsId);
	
	/**
	 * 修改購物車裡的商品數量
	 * @param uid 用戶ID
	 * @param goodsId 商品ID
	 * @param amount 調整值, 可以是正數, 也可以是負數
	 * @return 返回受影響的行數
	 */
	Integer changeGoodsCount(
		    @Param("uid") Integer uid, 
		    @Param("goodsId") Integer goodsId, 
		    @Param("amount") Integer amount);
	
}
