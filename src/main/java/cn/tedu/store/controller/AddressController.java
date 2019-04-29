package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ex.DataNotFoundException;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController {

	@Resource(name="addressService")
	private IAddressService addressService;
	
	@RequestMapping("/list.do")
	public String showList() {
		return "address_list";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/add.do")
	@ResponseBody
	public ResponseResult<Void> handleAdd(Address address, HttpSession session) {
		System.out.println("AddressController.handleAdd()");
		System.out.println("\t address: " + address);
		
		// 獲取uid
		Integer uid = getUidFromSession(session);
		// 將uid封裝到address中
		address.setUid(uid);
		// 執行增加
		addressService.add(address);
		// 創建返回值
		ResponseResult<Void> rr = new ResponseResult<Void>(1, "增加新收貨地址成功!");
		// 返回
		return rr;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public ResponseResult<Void> handleDelete(Integer id, HttpSession session) {
		// 聲明返回值
		ResponseResult<Void> rr;
		// 獲取當前登錄用戶ID
		Integer uid = getUidFromSession(session);
		// 獲取受影響的行數
		Integer affectedRows;
		// 根據結果創建不同的返回值
		try {
			affectedRows = addressService.delete(id, uid);
			rr = new ResponseResult<Void>(1, "刪除成功!");
		} catch (DataNotFoundException e) {
			rr = new ResponseResult<Void>(0, e);
			
		}
		
		return rr;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/handle_update.do")
	@ResponseBody
	public ResponseResult<Void> handleUpdate(Address address, HttpSession session) {
		// 聲明返回值
		ResponseResult<Void> rr;
		
		// 將uid從session中取出並封裝到address中
		Integer uid = getUidFromSession(session);
		address.setUid(uid);
		
		// 通過業務層執行修改
		Integer affectedRows = addressService.update(address);
		
		// 根據結果創建返回值
		if(affectedRows == 1) {
			rr = new ResponseResult<Void>(1, "修改成功!");
		} else {
			rr = new ResponseResult<Void>(0, "修改失敗!數據可能已被刪除, 或者當前登錄訊息有誤");
		}
		
		// 返回
		return rr;
	}
	
	@RequestMapping("/set_default.do")
	@ResponseBody
	public ResponseResult<Void> handleSetDefault(Integer id, HttpSession session) {
		ResponseResult<Void> rr;
		Integer uid = getUidFromSession(session);
		
		Integer affectedRows;
		try {
			affectedRows = addressService.setDefault(uid, id);
			rr = new ResponseResult<Void>(1, "設置默認地址成功!");
			
		} catch (DataNotFoundException e) {
			rr = new ResponseResult<Void>(0, e);
			
		}
		
		return rr;
	}
	
	@RequestMapping("/get.do")
	@ResponseBody
	public ResponseResult<Address> getAddressByIdAndUid(Integer id, HttpSession session) {
		ResponseResult<Address> rr;
		Integer uid = getUidFromSession(session);
		
		Address address = addressService.getAddressByIdAndUid(id, uid);
		rr = new ResponseResult<Address>(1, address);
		
		return rr;
	}
	
	@RequestMapping("/get_list.do")
	@ResponseBody
	public ResponseResult<List<Address>> getAddressListByUid(HttpSession session) {
		// 聲明返回值
		ResponseResult<List<Address>> rr;
		
		// 獲取當前登錄用戶ID
		Integer uid = getUidFromSession(session);
		
		// 通過addressService獲取當前登錄的用戶地址列表
		List<Address> addresses = addressService.getAddressListByUid(uid);
		
		// 創建返回值對象
		rr = new ResponseResult<List<Address>>(1, addresses);
		
		// 返回數據
		return rr;
	}
	
}
