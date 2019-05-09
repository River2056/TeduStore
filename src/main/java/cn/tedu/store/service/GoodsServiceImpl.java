package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.mapper.GoodsMapper;

@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService {
	
	@Resource(name="goodsMapper")
	private GoodsMapper goodsMapper;
	
	private Integer countPerPage = COUNT_PER_PAGE;
	
	public Integer getCountPerPage() {
		return this.countPerPage;
	}
	
	public void setCountPerPage(int countPerPage) {
		if(countPerPage >= 5 && countPerPage <= 30) {
			this.countPerPage = countPerPage;
		}
	}
	
	/**
	 * 與Mapper層同名方法;
	 * 參數: 分類ID, 排序, 偏移量, 數量 皆須指定
	 */
	public List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy, Integer offset, Integer count) {
		return goodsMapper.getGoodsListByCategoryId(categoryId, orderBy, offset, count);
	}
	
	/**
	 * 與Mapper層同名方法;
	 * 參數: 分類ID, 偏移量, 數量  -> 需指定
	 * 參數: 排序 -> 系統默認(優先級)
	 */
	public List<Goods> getGoodsListByCategoryId(Integer categoryId, Integer offset, Integer count) {
		return goodsMapper.getGoodsListByCategoryId(categoryId, ORDER_BY_DEFAULT, offset, count);
	}

	/**
	 * 與Mapper層同名方法;
	 * 參數: 分類ID, 排序, 頁碼  -> 需指定
	 * 按照頁碼算出偏移量, count為沒指定 -> 默認20
	 */
	public List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy, Integer page) {
		Integer offset = (page - 1) * getCountPerPage();
		Integer count = getCountPerPage();
		
		return goodsMapper.getGoodsListByCategoryId(categoryId, orderBy, offset, count);
	}

	/**
	 * 與Mapper層同名方法;
	 * 參數: 分類ID, 頁碼  -> 需指定
	 * 排序按照默認排序(優先級)
	 */
	public List<Goods> getGoodsListByCategoryId(Integer categoryId, Integer page) {
		return getGoodsListByCategoryId(categoryId, ORDER_BY_DEFAULT, page);
	}

	/**
	 * 與Mapper層同名方法;
	 * 參數: 分類ID, 排序  -> 需指定
	 * 排序自定義
	 * 只顯示第一頁
	 */
	public List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy) {
		return getGoodsListByCategoryId(categoryId, orderBy, 1);
	}

	/**
	 * 與Mapper層同名方法;
	 * 參數: 分類ID -> 需指定
	 * 排序按照默認, 且只顯示第一頁
	 */
	public List<Goods> getGoodsListByCategoryId(Integer categoryId) {
		return getGoodsListByCategoryId(categoryId, ORDER_BY_DEFAULT, 1);
	}
	
	/**
	 * 根據商品種類獲取商品訊息
	 */
	public List<Goods> getGoodsListByItemType(String itemType) {
		return goodsMapper.getGoodsListByItemType(itemType);
	}

	/**
	 * 獲取商品總數量
	 */
	public Integer getGoodsCountByCategoryId(Integer categoryId) {
		return goodsMapper.getGoodsCountByCategoryId(categoryId);
	}

	/**
	 * 根據商品ID獲取商品訊息
	 */
	public Goods getGoodsById(Integer id) {
		return goodsMapper.getGoodsById(id);
	}

	

}
