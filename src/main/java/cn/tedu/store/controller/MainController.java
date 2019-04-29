package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.service.IGoodsCategoryService;

@RequestMapping("/main")
@Controller
public class MainController extends BaseController {

	@Resource(name="goodsCategoryService")
	private IGoodsCategoryService goodsCategoryService;
	
	@RequestMapping("/index.do")
	public String showIndex(ModelMap modelMap) {
		// everything goes here
		List<List<GoodsCategory>> computerCategories = new ArrayList<List<GoodsCategory>>();
		
		// get computer categories list
		List<GoodsCategory> categories = goodsCategoryService.getGoodsCategoryListByParentId(161, 0, 3);
		computerCategories.add(categories);
		
		// get every id of computer categories, put it in a list
		List<GoodsCategory> subCategories;
		for(GoodsCategory goodsCategory : categories) {
			subCategories = goodsCategoryService.getGoodsCategoryListByParentId(goodsCategory.getId(), null, null);
			computerCategories.add(subCategories);
		}
		
		// package info and forward
		modelMap.addAttribute("computerCategories", computerCategories);
		
		// println results to check
		for(List<GoodsCategory> list : computerCategories) {
			for(GoodsCategory goodsCategory : list) {
				System.out.println(goodsCategory);
			}
		}
		
		// execute forward
		return "index";
	}
}
