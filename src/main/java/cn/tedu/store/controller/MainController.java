package cn.tedu.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main")
@Controller
public class MainController extends BaseController {

	@RequestMapping("/index.do")
	public String showIndex() {
		return "index";
	}
}
