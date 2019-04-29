import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Province;
import cn.tedu.store.service.IDistrictService;

public class TestDistrictService {

	@Test
	public void testDistrictServiceGetProvince() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IDistrictService districtService = ctx.getBean("districtService", IDistrictService.class);
		
		List<Province> provinces =  districtService.getProvince();
		for (Province p : provinces) {
			System.out.println(p);
		}
		
		ctx.close();
	}
	
	@Test
	public void testDistrictServiceGetProvinceNameByCode() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IDistrictService districtService = ctx.getBean("districtService", IDistrictService.class);
		
		String provinceCode = "510000";
		String provinceName = districtService.getProvinceNameByCode(provinceCode);
		
		System.out.println(provinceCode + " -> " + provinceName); 
		
		ctx.close();
	}
	
	@Test
	public void testDistrictServiceGetCityNameByCode() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IDistrictService districtService = ctx.getBean("districtService", IDistrictService.class);
		
		String cityCode = "510100";
		String cityName = districtService.getCityNameByCode(cityCode);
		
		System.out.println(cityCode + " -> " + cityName); 
		
		ctx.close();
	}
	
	@Test
	public void testDistrictServiceGetAreaNameByCode() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IDistrictService districtService = ctx.getBean("districtService", IDistrictService.class);
		
		String areaCode = "510104";
		String areaName = districtService.getAreaNameByCode(areaCode);
		
		System.out.println(areaCode + " -> " + areaName); 
		
		ctx.close();
	}
	
}
