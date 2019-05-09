package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.Cart;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Resource(name="addressService")
	private IAddressService addressService;
	
	@Resource(name="cartService")
	private ICartService cartService;
	
	@RequestMapping("/confirm.do")
	public String handleConfirmOrder(HttpSession session, String cartId, ModelMap modelMap) {
		// ***** 獲取用戶的收貨人地址列表 *****
		// 從session中獲取uid
		Integer uid = getUidFromSession(session);
		// 根據uid獲取收貨人地址列表
		List<Address> addressList = addressService.getAddressListByUid(uid);
		// 測試輸出
		System.out.println("OrderController.handleConfirmOrder()");
		System.out.println("加載當前用戶的收貨人地址列表...");
		for (Address address : addressList) {
			System.out.println("\t" + address);
		}
		
		// ***** 獲取所選的購物車商品訊息 *****
		// 聲明集合, 用於存儲購物車的商品訊息
		List<Cart> cartList = new ArrayList<Cart>();
		
		// 拆分cartId
		String[] ids = cartId.split(",");
		// 循環遍歷數組中所有id, 將其轉換為Integer, 並獲取Cart商品, 再存進cartList封裝轉發, 以便前端顯示
		for (String idString : ids) {
			Integer id = Integer.valueOf(idString);
			Cart cartItem = cartService.getCartById(uid, id);
			cartList.add(cartItem);
		}
		// 測試輸出
		System.out.println("加載當前用戶的選中購物車商品列表至訂單中...");
		for (Cart cartItem : cartList) {
			System.out.println("\t" + cartItem);
		}
		
		// 封裝數據已準備轉發
		modelMap.addAttribute("addressList", addressList);
		modelMap.addAttribute("cartList", cartList);
		
		return "orderConfirm";
	}
}
