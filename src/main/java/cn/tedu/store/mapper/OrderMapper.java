package cn.tedu.store.mapper;

import cn.tedu.store.bean.Order;
import cn.tedu.store.bean.OrderItem;

public interface OrderMapper {

	/**
	 * 新增一條新的訂單紀錄
	 * @param order 訂單訊息
	 * @return 返回受影響的行數
	 */
	Integer insertOrder(Order order);
	
	/**
	 * 新增一條訂單中的商品購買紀錄
	 * @param item 商品詳情
	 * @return 返回受影響的行數
	 */
	Integer insertOrderItem(OrderItem item);
}
