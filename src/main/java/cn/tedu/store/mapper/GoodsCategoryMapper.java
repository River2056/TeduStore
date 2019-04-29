package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.GoodsCategory;

public interface GoodsCategoryMapper {
	
	/**
	 * 根據父級分類ID獲取商品分類列表
	 * @param parentId 父級分類ID
	 * @param offset 偏移量, 即跳過前面多少條數據, 如果從頭開始獲取, 應該設置為0, 如果不需要分頁, 該參數值取null值即可
	 * @param count 獲取數據的數量
	 * @return 返回商品分類列表, 如果沒有匹配的數據, 則返回長度為0的List集合
	 */
	List<GoodsCategory> getGoodsCategoryListByParentId(
			@Param("parentId") Integer parentId, 
			@Param("offset") Integer offset, 
			@Param("count") Integer count);
}
