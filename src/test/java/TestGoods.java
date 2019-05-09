import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IGoodsService;

public class TestGoods {

	@Test
	public void testGetList() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		GoodsMapper mapper = ctx.getBean("goodsMapper", GoodsMapper.class);
		
		Integer categoryId = 163;
		String orderBy = " priority desc";
		Integer offset = 0;
		Integer count = 3;
		List<Goods> goodsList = mapper.getGoodsListByCategoryId(categoryId, orderBy, offset, count);
		for(Goods goods : goodsList) {
			System.out.println(goods);
		}
		
		ctx.close();
	}
	
	@Test
	public void testServiceGetList() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IGoodsService goodsService = ctx.getBean("goodsService", IGoodsService.class);
		
		Integer categoryId = 163;
		String orderBy = " priority desc";
		Integer offset = 0;
		Integer count = 3;
		Integer page = 1;
		goodsService.setCountPerPage(8);
		
		List<Goods> goodsList = goodsService.getGoodsListByCategoryId(categoryId, page);
		for(Goods goods : goodsList) {
			System.out.println(goods.getId() + ", " + goods.getPriority() + ", " + goods.getTitle());
		}
		
		ctx.close();
	}
	
	@Test
	public void testGetCount() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		GoodsMapper mapper = ctx.getBean("goodsMapper", GoodsMapper.class);
		
		Integer categoryId = 163;
		Integer count = mapper.getGoodsCountByCategoryId(categoryId);
		
		System.out.println("count: " + count);
		
		ctx.close();
	}
}
