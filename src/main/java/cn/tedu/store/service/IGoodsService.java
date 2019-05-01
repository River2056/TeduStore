package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Goods;

public interface IGoodsService {
	/**
	 * 查詢商品默認排序, 默認按照優先排序
	 */
	String ORDER_BY_DEFAULT = "priority DESC";
	
	/**
	 * 查詢商品按照價格遞增做排序
	 */
	String ORDER_BY_PRICE_ASC = "price ASC";
	
	/**
	 * 查詢商品按照價格遞減做排序
	 */
	String ORDER_BY_PRICE_DESC = "price DESC";
	
	/**
	 * 每頁顯示多少條數據, 默認常量為20條
	 */
	Integer COUNT_PER_PAGE = 20;
	
	/**
	 * 設置每頁的商品數量
	 * @param countPerPage
	 */
	void setCountPerPage(int countPerPage);
	
	/**
	 * 獲取每頁顯示的商品數量
	 * @return 每頁顯示的商品數量
	 */
	Integer getCountPerPage();

	/**
	 * 根據商品分類ID獲取商品清單 
	 * @param categoryId 商品分類ID
	 * @param orderBy 排序語句
	 * @param offset 偏移量, 即跳過前面多少條數據, 如果從頭開始獲取, 應該設置為0, 如果不需要分頁, 該參數值取null值即可
	 * @param count 獲取數據的數量
	 * @return 返回商品的集合, 如果沒有, 則返回長度為0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy, Integer offset, Integer count);
	
	/**
	 * 根據商品分類ID獲取商品清單, 獲取到的商品將按照優先級(priority)排序
	 * @param categoryId 商品分類ID
	 * @param offset 偏移量, 即跳過前面多少條數據, 如果從頭開始獲取, 應該設置為0, 如果不需要分頁, 該參數值取null值即可
	 * @param count 獲取數據的數量
	 * @return 返回商品的集合, 如果沒有, 則返回長度為0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, Integer offset, Integer count);
	
	/**
	 * 根據商品分類ID獲取商品清單
	 * @param categoryId 商品分類ID
	 * @param orderBy 指定排序方法
	 * @param page 數據的頁碼, 即獲取第幾頁的數據
	 * @return 返回商品的集合, 如果沒有, 則返回長度為0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy, Integer page);
	
	/**
	 * 根據商品分類ID獲取商品清單, 獲取到的商品將按照優先級(priority)排序
	 * @param categoryId 商品分類ID
	 * @param page 數據的頁碼, 即獲取第幾頁的數據
	 * @return 返回商品的集合, 如果沒有, 則返回長度為0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, Integer page);
	
	/**
	 * 根據商品分類ID獲取商品清單, 並且指定排序, 不指定頁碼表示只要第一頁
	 * @param categoryId 商品分類ID
	 * @param orderBy 指定排序方法
	 * @return 返回商品的集合, 如果沒有, 則返回長度為0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy);
	
	/**
	 * 根據商品分類ID獲取商品清單, 只指定商品分類ID, 排序預設(按照優先級priority排序), 只要第一頁
	 * @param categoryId 商品分類ID
	 * @return 返回商品的集合, 如果沒有, 則返回長度為0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId);
}
