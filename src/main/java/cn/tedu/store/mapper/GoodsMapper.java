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
}
