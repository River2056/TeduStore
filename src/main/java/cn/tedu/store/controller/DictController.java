package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.IDistrictService;

@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

	/**
	 *  /dict/province.do
	 *  	請求方式: GET
	 *  	參數: 無
	 *  	響應方式: ResponseBody, json
	 *  	修改攔截器配置:否
	 *  
	 *  /dict/cities.do
	 *  	請求方式: GET
	 *  	參數: 省的編號 province_code=100000
	 *  	響應方式: ResponseBody, json
	 *  	修改攔截器配置:否
	 *  
	 *  /dict/areas.do
	 *  	請求方式: GET
	 *  	參數: 市的編號 city_code=110000
	 *  	響應方式: ResponseBody, json
	 *  	修改攔截器配置:否
	 */
	
	@Resource(name="districtService")
	private IDistrictService districtService;
	
	@RequestMapping("/provinces.do")
	@ResponseBody
	public ResponseResult<List<Province>> getProvinces() {
		ResponseResult<List<Province>> rr;
		List<Province> provinces = districtService.getProvince();
		
		rr = new ResponseResult<List<Province>>(1, provinces);
		
		return rr;
	}
	
	@RequestMapping("/cities.do")
	@ResponseBody
	public ResponseResult<List<City>> getCities(String provinceCode) {
		ResponseResult<List<City>> rr;
		List<City> cities = districtService.getCities(provinceCode);
		
		rr = new ResponseResult<List<City>>(1, cities);
		
		return rr;
	}
	
	@RequestMapping("/areas.do")
	@ResponseBody
	public ResponseResult<List<Area>> getAreas(String cityCode) {
		ResponseResult<List<Area>> rr;
		List<Area> areas = districtService.getAreas(cityCode);
		
		rr = new ResponseResult<List<Area>>(1, areas);
		
		return rr;
	}
}
