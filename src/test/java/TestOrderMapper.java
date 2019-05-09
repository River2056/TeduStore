import java.util.Date;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Order;
import cn.tedu.store.bean.OrderItem;
import cn.tedu.store.mapper.OrderMapper;

public class TestOrderMapper {

	@Test
	public void testOrderInsert() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		OrderMapper orderMapper = ctx.getBean("orderMapper", OrderMapper.class);
		
		// Dummy test data
		Order order = new Order();
		order.setUid(5);
		order.setRecvName("董晉誠");
		order.setRecvDistrict("台灣省新北市板橋區");
		order.setRecvAddr("縣民大道三段33巷9弄17號");
		order.setRecvZip("22042");
		order.setRecvPhone("0938929201");
		order.setRecvTel("073319488");
		order.setStatus(1);
		order.setCreatedUser("System Admin");
		order.setCreatedTime(new Date());
		order.setModifiedUser("System Admin");
		order.setModifiedTime(new Date());
		
		OrderItem item = new OrderItem();
		item.setOrderId(order.getId());
		item.setGoodsImage("src/test/image.png");
		item.setGoodsTitle("超強筆電");
		item.setGoodsItemType("筆電");
		item.setGoodsPrice(50000);
		item.setGoodsCount(1);
		item.setCreatedUser("System Admin");
		item.setCreatedTime(new Date());
		item.setModifiedUser("System Admin");
		item.setModifiedTime(new Date());
		
		OrderItem item2 = new OrderItem();
		item2.setOrderId(order.getId());
		item2.setGoodsImage("src/test/image.png");
		item2.setGoodsTitle("超強筆電2");
		item2.setGoodsItemType("筆電2");
		item2.setGoodsPrice(45688);
		item2.setGoodsCount(1);
		item2.setCreatedUser("System Admin");
		item2.setCreatedTime(new Date());
		item2.setModifiedUser("System Admin");
		item2.setModifiedTime(new Date());
		
		Integer totalPrice = item.getGoodsPrice() + item2.getGoodsPrice();
		order.setTotalPrice(totalPrice);
		
		Integer affectedRows = orderMapper.insertOrder(order);
		Integer affectedRows2 = orderMapper.insertOrderItem(item);
		Integer affectedRows3 = orderMapper.insertOrderItem(item2);
		System.out.println("紀錄完成!, 受影響的行數; order: " + affectedRows + ", orderItem1: " + affectedRows2 + ", orderItem2: " + affectedRows3);
		
		ctx.close();
	}
}
