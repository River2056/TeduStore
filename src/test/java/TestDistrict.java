import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;
import cn.tedu.store.mapper.DistrictMapper;

public class TestDistrict {

	@Test
	public void testDistrictGetProvince() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		DistrictMapper districtMapper = ctx.getBean("districtMapper", DistrictMapper.class);
		
		List<Province> province = districtMapper.getProvince();
		for(Province p : province) {
			System.out.println(p);
		}
		
		ctx.close();
	}
	
	@Test
	public void testDistrictGetCities() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		DistrictMapper districtMapper = ctx.getBean("districtMapper", DistrictMapper.class);
		
		String provinceCode = "210000";
		List<City> cities = districtMapper.getCities(provinceCode);
		for(City c : cities) {
			System.out.println(c);
		}
		
		ctx.close();
	}
	
	@Test
	public void testDistrictGetArea() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		DistrictMapper districtMapper = ctx.getBean("districtMapper", DistrictMapper.class);
		
		String cityCode = "110100";
		List<Area> areas = districtMapper.getArea(cityCode);
		for(Area a : areas) {
			System.out.println(a);
		}
		
		ctx.close();
	}
}
