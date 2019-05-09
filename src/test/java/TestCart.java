import java.util.Date;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;

public class TestCart {

	@Test
	public void testMapperAdd() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		CartMapper cartMapper = ctx.getBean("cartMapper", CartMapper.class);
		
		Cart cart = new Cart();
		cart.setUid(5);
		cart.setGoodsId(100017);
		cart.setGoodsImage("src/images/computer.png");
		cart.setGoodsTitle("超薄筆電");
		cart.setGoodsItemType("電腦");
		cart.setGoodsPrice(50000);
		cart.setGoodsCount(1);
		cart.setCreatedUser("kevin777");
		cart.setCreatedTime(new Date());
		cart.setModifiedUser("kevin777");
		cart.setModifiedTime(new Date());
		
		Integer affectedRows = cartMapper.add(cart);
		System.out.println("增加成功! 影響行數: " + affectedRows);
		
		ctx.close();
	}
	
	@Test
	public void testServiceAdd() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		ICartService cartService = ctx.getBean("cartService", ICartService.class);
		
		Cart cart = new Cart();
		cart.setUid(5);
		cart.setGoodsId(100019);
		cart.setGoodsImage("src/images/computerXXXCCC.png");
		cart.setGoodsTitle("工作筆電");
		cart.setGoodsItemType("電腦");
		cart.setGoodsPrice(90000);
		cart.setGoodsCount(5);
		cart.setCreatedUser("kevin777");
		cart.setCreatedTime(new Date());
		cart.setModifiedUser("kevin777");
		cart.setModifiedTime(new Date());
		
		Integer itemId = cartService.add(cart);
		System.out.println("增加成功! 物品ID: " + itemId);
		
		ctx.close();
	}
	
	@Test
	public void testGetCount() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		ICartService cartService = ctx.getBean("cartService", ICartService.class);
		
		Integer uid = 5;
		Integer goodsId = 10000022;
		Integer count = cartService.getRecordCount(uid, goodsId);
		System.out.println("紀錄裡有: " + count);
		
		ctx.close();
	}
	
	@Test
	public void testChangeGoodsCount() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		ICartService cartService = ctx.getBean("cartService", ICartService.class);
		
		Integer uid = 5;
		Integer goodsId = 100019;
		Integer amount = -10;
		Integer affectedRows = cartService.changeGoodsCount(uid, goodsId, amount);
		System.out.println("修改成功, 受影響的行數: " + affectedRows);
		
		ctx.close();
	}
}
