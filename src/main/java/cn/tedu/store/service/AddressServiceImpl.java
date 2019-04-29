package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.bean.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.ex.DataNotFoundException;

@Service("addressService")
public class AddressServiceImpl implements IAddressService {

	@Resource(name="addressMapper")
	private AddressMapper addressMapper;
	
	@Resource(name="districtService")
	private IDistrictService districtService;
	
	/**
	 * 增加地址
	 * @return 返回新地址的ID
	 */
	public Integer add(Address address) {
		// 由Controller提交的數據並沒有recvDistrict字段
		// 此時需要得到該字段的值, 並封裝到address中
		String recvDistrict = getRecvDistrict(address);
		address.setRecvDistrict(recvDistrict);
		// 確定當前收貨地址是否為默認地址
		// 僅當目前的收貨地址是該用戶得第一條時, 設置為默認
		List<Address> addresses = addressMapper.getAddressListByUid(address.getUid());
		// if user doesn't have an address, set the first one as default
		// else, don't set default
		Integer isDefault = addresses.size() == 0 ? 1 : 0;
		
		address.setIsDefault(isDefault);
		
		// 執行增加
		addressMapper.insert(address);
		
		// 獲取ID
		Integer id = address.getId();
		
		return id;
	}
	
	/**
	 * 根據登錄用戶的uid查詢這個用戶的所有收貨人地址
	 * @param uid 當前登錄用戶的ID
	 * @return 返回這個用戶的所有收貨人地址
	 */
	public List<Address> getAddressListByUid(Integer uid) {
		return addressMapper.getAddressListByUid(uid);
	}
	
	/**
	 * 與Mapper層同名方法: getAddressByIdAndUid
	 * 根據數據ID和當前登錄用戶ID查找一條地址(填表單用)
	 */
	public Address getAddressByIdAndUid(Integer id, Integer uid) {
		return addressMapper.getAddressByIdAndUid(id, uid);
	}
	
	/**
	 * 業務層Service刪除方法: delete
	 * 會先判斷該條數據是否為默認地址, 是的話設置一個新的默認地址
	 */
	@Transactional
	public Integer delete(Integer id, Integer uid) {
		// 調整, 考慮各種情景
		Integer affectedRows = 0;
		// 獲取此次需要刪除的地址信息
		Address address = getAddressByIdAndUid(id, uid);
		// 判斷查詢結果是否為null
		if(address == null) {
			// 結果為null, 拋出DataNotFoundException
			throw new DataNotFoundException("嘗試刪除的數據不存在!請刷新後再操作");
			
		} else {
			// 查詢結果不為null, 獲取數據成功, 即將執行刪除...
			affectedRows = addressMapper.delete(id, uid);
			if(affectedRows == 0) {
				throw new DataNotFoundException("刪除失敗!");
			}
			if(address.getIsDefault() == 1) {
				// need to set a new default address after delete
				// get all address list of that user
				List<Address> addresses = getAddressListByUid(uid);
				
				if(addresses.size() > 0) {
					// means there's still some left, set the first one as new default
					// else if no addresses left -> do nothing
					Address newDefault = addresses.get(0);
					affectedRows = setDefault(uid, newDefault.getId());
					if(affectedRows == 0) {
						throw new DataNotFoundException("設置默認失敗! 數據可能已經被刪除, 請刷新後重試");
					}
				}
				
			} else {
				// if it's not a default address, just proceed
				
			}
			
		}
		
		return affectedRows;
	}

	/**
	 * 與Mapper層同名方法: update
	 */
	public Integer update(Address address) {
		// 獲取省市區的名稱
		String recvDistrict = getRecvDistrict(address);
		// 在參數中封裝省市區的名稱
		address.setRecvDistrict(recvDistrict);
		// 執行修改
		return addressMapper.update(address);
	}

	/**
	 * 業務層Service: setDefault
	 * 將某一條地址設為默認
	 * Transactional -> 事務
	 */
	@Transactional
	public Integer setDefault(Integer uid, Integer id) {
		// 先將該用戶的所有收貨地址設置為非默任
		Integer affectedRows;
		affectedRows = addressMapper.cancelAllDefault(uid);
		if(affectedRows == 0) {
			// didn't found any data to cancel default, throw exception
			throw new DataNotFoundException("設置默認失敗!沒有查詢到用戶的任何收貨地址!");
		}
		affectedRows = addressMapper.setDefault(uid, id);
		if(affectedRows == 0) {
			// 受影響的行數為0
			// 即設置默認時, 這條數據已經不存在
			// 則事務回滾
			throw new DataNotFoundException("設置默認失敗! 數據可能已經被刪除, 請刷新後重試");
		}
		
		return affectedRows;
	}
	
	/**
	 * 獲取收貨人地址的省市區的名稱
	 * @param address 至少封裝了收貨人地址的省市區對應編碼
	 * @return 返回省市區名稱
	 */
	private String getRecvDistrict(Address address) {
		// 獲取省名稱
		String provinceCode = address.getRecvProvince();
		String provinceName = districtService.getProvinceNameByCode(provinceCode);
		
		// 獲取市名稱
		String cityCode = address.getRecvCity();
		String cityName = districtService.getCityNameByCode(cityCode);
		
		// 獲取區名稱
		String areaCode = address.getRecvArea();
		String areaName = districtService.getAreaNameByCode(areaCode);
		
		return provinceName + cityName + areaName;
	}

	

	

}
