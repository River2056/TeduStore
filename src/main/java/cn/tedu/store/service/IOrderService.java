package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Order;
import cn.tedu.store.bean.OrderItem;

public interface IOrderService {

	/**
	 * 創建訂單
	 * @param order 訂單數據
	 * @param orderItems 訂單中的商品數據
	 * @throws 將會拋出異常OrderCreationException 創建訂單失敗的異常
	 */
	void createOrder(Order order, List<OrderItem> orderItems);
}
