import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.mapper.GoodsCategoryMapper;

public class TestGoodsCategory {
	
	@Test
	public void testGetList() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		GoodsCategoryMapper mapper = ctx.getBean("goodsCategoryMapper", GoodsCategoryMapper.class);
		
		Integer parentId = 161;
		Integer offset = 0;
		Integer count = 3;
		List<GoodsCategory> categories = mapper.getGoodsCategoryListByParentId(parentId, offset, count);
		for(GoodsCategory goodsCategory : categories) {
			System.out.println(goodsCategory);
		}
		
		ctx.close();
	}
}
