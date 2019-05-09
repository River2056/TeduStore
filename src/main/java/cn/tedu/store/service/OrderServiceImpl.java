package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.bean.Order;
import cn.tedu.store.bean.OrderItem;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.ex.OrderCreationException;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {

	@Resource(name="orderMapper")
	private OrderMapper orderMapper;
	
	@Transactional
	public void createOrder(Order order, List<OrderItem> orderItems) {
		// 使用變量獲取每次操作的返回值
		Integer affectedRows;
		// 創建訂單失敗時的提示文字
		String exceptionMessage = "創建訂單失敗!";
		// 增加訂單
		affectedRows = orderMapper.insertOrder(order);
		// 判斷訂單是否成功
		if(affectedRows != 1) {
			// means insert fail, throw OrderCreationException
			throw new OrderCreationException(exceptionMessage);
			
		}
		// 增加訂單商品
		for(OrderItem item : orderItems) {
			// 執行增加, 並獲取返回值
			affectedRows = orderMapper.insertOrderItem(item);
			// 判斷訂單商品是否增加成功
			if(affectedRows != 1) {
				throw new OrderCreationException(exceptionMessage);
				
			}
		}
	}

}
