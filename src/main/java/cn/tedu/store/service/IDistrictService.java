package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;

public interface IDistrictService {

	/**
	 * 獲取所有省的列表
	 * @return 所有省的列表
	 */
	List<Province> getProvince();
	
	/**
	 * 獲取某個省的市列表
	 * @param provinceCode 省的編號
	 * @return 某個省的市列表
	 */
	List<City> getCities(String provinceCode);
	
	/**
	 * 獲取某個市的區列表
	 * @param cityCode 市的編號
	 * @return 某個市的區列表
	 */
	List<Area> getAreas(String cityCode);
	
	/*
	 * 以下為利用對應編碼尋找對應名子
	 */
	/**
	 * 利用省的代號尋找省的名字
	 * @param provinceCode 省代號
	 * @return 返回省名稱
	 */
	String getProvinceNameByCode(String provinceCode);
	
	/**
	 * 利用城市的代號尋找城市的名字
	 * @param cityCode 城市代號
	 * @return 返回城市名稱
	 */
	String getCityNameByCode(String cityCode);
	
	/**
	 * 利用區的代號尋找區的名字
	 * @param areaCode 區代號
	 * @return 返回區名稱
	 */
	String getAreaNameByCode(String areaCode);
}
