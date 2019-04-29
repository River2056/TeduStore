package cn.tedu.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 日誌
		System.out.println("LoginInterceptor.preHandle() get session");
		System.out.println("\t request: " + request);
		System.out.println("\t response: " + response);
		System.out.println("\t handler: " + handler);
		
		// 獲取HttpSession對象
		HttpSession session = request.getSession();
		
		// 判斷是否登入
		if(session.getAttribute("uid") == null) {
			// 沒有登入, 則重定向到主頁
			String url = request.getContextPath() + "/main/index.do";
			response.sendRedirect(url);
			// 攔截, 不繼續往後處理請求了(例如沒登入直接進修改密碼頁面)
			System.out.println();
			return false;
		}
		
		// 放行
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// 日誌
				System.out.println("LoginInterceptor.postHandle()");
				System.out.println("\t request: " + request);
				System.out.println("\t response: " + response);
				System.out.println("\t Object handler: " + handler);
				System.out.println("\t ModelAndView modelAndView: " + modelAndView);
				
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		// 日誌
				System.out.println("LoginInterceptor.afterCompletion()");
				System.out.println("\t request: " + request);
				System.out.println("\t response: " + response);
				System.out.println("\t Object handler: " + handler);
				System.out.println("\t Exception ex: " + ex);
	}

}
