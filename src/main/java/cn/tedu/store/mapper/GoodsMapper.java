package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Goods;

public interface GoodsMapper {

	/**
	 * 根據商品分類ID獲取商品清單 
	 * @param categoryId 商品分類ID
	 * @param orderBy 排序語句
	 * @param offset 偏移量, 即跳過前面多少條數據, 如果從頭開始獲取, 應該設置為0, 如果不需要分頁, 該參數值取null值即可
	 * @param count 獲取數據的數量
	 * @return 返回商品的集合, 如果沒有, 則返回長度為0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(
			@Param("categoryId") Integer categoryId, 
			@Param("orderBy") String orderBy, 
			@Param("offset") Integer offset, 
			@Param("count") Integer count);
	
	/**
	 * 根據種類獲取商品訊息
	 * @param itemType 商品種類
	 * @return 返回商品的List集合
	 */
	List<Goods> getGoodsListByItemType(String itemType);
	
	
	/**
	 * 獲取某分類的商品數量總數
	 * @param categoryId 分類ID
	 * @return 返回商品數量
	 */
	Integer getGoodsCountByCategoryId(Integer categoryId);
	
	/**
	 * 根據商品ID獲取商品訊息
	 * @param id 商品ID
	 * @return 返回匹配的商品數據, 如果沒有匹配數據, 則返回null
	 */
	Goods getGoodsById(Integer id);
}
