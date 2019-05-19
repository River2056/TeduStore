package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.DataNotFoundException;
import cn.tedu.store.service.ex.ServiceException;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

	@Resource(name="cartService")
	private ICartService cartService;
	
	@RequestMapping("/list.do")
	public String showList(HttpSession session, ModelMap modelMap) {
		// 獲取uid
		Integer uid = getUidFromSession(session);
		// 獲取數據
		List<Cart> userCartList = cartService.getCartList(uid);
		// 封裝數據, 準備轉發
		modelMap.addAttribute("userCartList", userCartList);
		// 執行轉發
		return "cart_list";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/add.do")
	@ResponseBody
	public ResponseResult<Void> handleAddToCart(Cart cart, HttpSession session) {
		// 聲明返回值
		ResponseResult<Void> rr;
		
		// 獲取uid
		Integer uid = getUidFromSession(session);
		
		// 封裝uid
		cart.setUid(uid);
		
		try {
			cartService.add(cart);
			rr = new ResponseResult<Void>(1, "加入購物車成功!");
			
		} catch (ServiceException e) {
			rr = new ResponseResult<Void>(0, e);
			
		}
		
		return rr;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public ResponseResult<Void> handleDeleteCartItem(Integer id, HttpSession session) {
		ResponseResult<Void> rr;
		Integer uid = getUidFromSession(session);
		try {
			cartService.remove(uid, id);
			rr = new ResponseResult<Void>(1, "刪除成功!");
			
		} catch (DataNotFoundException e) {
			rr = new ResponseResult<Void>(0, e);
			
		}
		return rr;
	}
	
	
}
