import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import cn.tedu.store.bean.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;

public class TestAddress {
	
	DataSourceTransactionManager dstm;

	@Test
	public void testMapperInsert() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		AddressMapper addressMapper = ctx.getBean("addressMapper", AddressMapper.class);
		
		Address address = new Address();
		address.setUid(1);
		address.setRecvName("Tom");
		address.setRecvProvince("100000");
		address.setRecvCity("110000");
		address.setRecvArea("111000");
		address.setRecvDistrict("台灣省新北市板橋區");
		address.setRecvAddr("縣民大道三段巷9弄17號5樓");
		address.setRecvPhone("0938929201");
		Integer affectedRows = addressMapper.insert(address);
		System.out.println("增加新地址完成! " + affectedRows);
		
		ctx.close();
	}
	
	@Test
	public void testServiceAddressList() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IAddressService addressService = ctx.getBean("addressService", IAddressService.class);
		
		Integer uid = 5;
		List<Address> addresses = addressService.getAddressListByUid(uid);
		
		for(Address addr : addresses) {
			System.out.println(addr);
		}
		
		ctx.close();
	}
	
	@Test
	public void testMapperDelete() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		AddressMapper addressMapper = ctx.getBean("addressMapper", AddressMapper.class);
		
		Integer affectedRows = addressMapper.delete(4, 5);
		System.out.println("成功刪除! 影響行數: " + affectedRows);
		
		ctx.close();
	}
	
	@Test
	public void testMapperSearchOneAddress() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		AddressMapper addressMapper = ctx.getBean("addressMapper", AddressMapper.class);
		
		Integer id = 8;
		Integer uid = 5;
		Address address = addressMapper.getAddressByIdAndUid(id, uid);
		System.out.println("搜尋結果為: " + "\n" + address);
		
		ctx.close();
	}
	
	@Test
	public void testServiceSetDefault() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IAddressService addressService = ctx.getBean("addressService", IAddressService.class);
		
		Integer uid = 5;
		Integer id = 8;
		Integer affectedRows = addressService.setDefault(uid, id);
		System.out.println("設置默認成功, 影響行數: " + affectedRows);
		
		ctx.close();
	}
}
