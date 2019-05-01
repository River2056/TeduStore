package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.service.IGoodsService;

@RequestMapping("/main")
@Controller
public class MainController extends BaseController {

	@Resource(name="goodsCategoryService")
	private IGoodsCategoryService goodsCategoryService;
	
	@Resource(name="goodsService")
	private IGoodsService goodsService;
	
	@RequestMapping("/index.do")
	public String showIndex(ModelMap modelMap) {
		// 聲明電腦分類的數據
		List<List<GoodsCategory>> computerCategories = new ArrayList<List<GoodsCategory>>();
		// 聲明電腦分類中排名前3的商品
		List<Goods> computers;
		
		// get computer categories list
		List<GoodsCategory> categories161 = goodsCategoryService.getGoodsCategoryListByParentId(161, 0, 3);
		// computerCategories.add(categories);
		
		// get every id of computer categories, put it in a list
		List<GoodsCategory> subCategories;
		for(GoodsCategory goodsCategory : categories161) {
			subCategories = goodsCategoryService.getGoodsCategoryListByParentId(goodsCategory.getId(), null, null);
			computerCategories.add(subCategories);
		}
		
		computers = goodsService.getGoodsListByCategoryId(163, 0, 3);
		
		// println results to check
		System.out.println("MainController.showIndex()");
		
		System.out.println("headers list:");
		for(GoodsCategory menu : categories161) {
			System.out.println(menu.getName());
		}
		
		System.out.println("details list:");
		for(List<GoodsCategory> list : computerCategories) {
			for(GoodsCategory goodsCategory : list) {
				System.out.println(goodsCategory.getName());
			}
		}
		
		System.out.println("\t 電腦前三名商品: ");
		for(Goods good : computers) {
			System.out.println(good);
		}
		
		// package info and forward
		modelMap.addAttribute("categories161", categories161);
		modelMap.addAttribute("computerCategories", computerCategories);
		modelMap.addAttribute("computers", computers);
		
		// execute forward
		return "index";
	}
}
