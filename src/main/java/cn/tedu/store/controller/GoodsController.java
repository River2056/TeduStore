package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.service.IGoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

	@Resource(name="goodsService")
	private IGoodsService goodsService;
	
	@RequestMapping("/list.do")
	public String showGoodsListByCategoryId(
			@RequestParam(value="category_id", required=true) Integer categoryId, 
			@RequestParam(value="order_by", required=false) Integer orderBy, 
			@RequestParam(value="page", required=false) Integer page, 
			ModelMap modelMap) {
		// 聲明需要轉發的數據
		List<Goods> goodsList;
		// 商品總數量
		Integer goodsCount;
		// 分頁頁數
		Integer pages;
		// 設置每頁顯示多少條商品
		goodsService.setCountPerPage(IGoodsService.COUNT_PER_PAGE);
		// 預設顯示商品個數
		Integer countPerPage = goodsService.getCountPerPage();
		
		// 判斷參數
		if(categoryId == null || categoryId < 1) {
			modelMap.addAttribute("msg", "請求參數有誤!");
			return "error";
		}
		
		if(page == null || page < 1) {
			page = 1;
		}
		
		String orderByStr;
		if(orderBy == null || orderBy < 0 || orderBy >= IGoodsService.ORDER_BY.length) {
			orderBy = 0;
			
		}
		orderByStr = IGoodsService.ORDER_BY[orderBy];
		
		// 獲取數據
		goodsList = goodsService.getGoodsListByCategoryId(categoryId, orderByStr, page);
		goodsCount = goodsService.getGoodsCountByCategoryId(categoryId);
		// 算出商品頁數有多少頁, 有餘數必須追加1頁去顯示
		pages = goodsCount / countPerPage;
		pages += goodsCount % countPerPage == 0 ? 0 : 1;
		
		
		// 測試
		// 在控制台上輸出
		System.out.println("GoodsController.getGoodsListByCategoryId()");
		System.out.println("\t goodsCount: " + goodsCount);
		System.out.println("\t pages: " + pages);
		System.out.println("\t default display item quantity:" + countPerPage);
		for(Goods goods : goodsList) {
			System.out.println(goods);
		}
		
		// 轉發數據
		modelMap.addAttribute("goodsList", goodsList);
		modelMap.addAttribute("goodsCount", goodsCount);
		modelMap.addAttribute("pages", pages);
		modelMap.addAttribute("countPerPage", countPerPage);
		modelMap.addAttribute("categoryId", categoryId);
		modelMap.addAttribute("currentPage", page);
		
		// 執行轉發
		return "goods_list"; // jsp文件名
	}
	
	@RequestMapping("/details.do")
	public String showGoodsDetails(
			@RequestParam(value="id", required=true) Integer id, 
			ModelMap modelMap) {
		// 聲明需要轉發到jsp的數據
		Goods goods;
		
		// 判斷數據
		goods = goodsService.getGoodsById(id);
		if(goods != null) {
			// 獲取同類型商品
			List<Goods> goodsList = goodsService.getGoodsListByItemType(goods.getItemType());
			// 封裝需要轉發的數據
			modelMap.addAttribute("goods", goods);
			modelMap.addAttribute("goodsList", goodsList);
			
			// 測試
			System.out.println("GoodsController.showGoodsDetails()");
			System.out.println("goods: " + goods);
			for(Goods other : goodsList) {
				System.out.println("goodsList: " + other);
				
			}
			
			// 執行轉發
			return "goods_details";
			
		} else {
			// 沒有獲取到數據
			return "error";
			
		}
		
		
	}
	
}
