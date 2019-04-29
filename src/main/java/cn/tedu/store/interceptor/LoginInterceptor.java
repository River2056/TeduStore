package cn.tedu.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// ��x
		System.out.println("LoginInterceptor.preHandle() get session");
		System.out.println("\t request: " + request);
		System.out.println("\t response: " + response);
		System.out.println("\t handler: " + handler);
		
		// ���HttpSession��H
		HttpSession session = request.getSession();
		
		// �P�_�O�_�n�J
		if(session.getAttribute("uid") == null) {
			// �S���n�J, �h���w�V��D��
			String url = request.getContextPath() + "/main/index.do";
			response.sendRedirect(url);
			// �d�I, ���~�򩹫�B�z�ШD�F(�Ҧp�S�n�J�����i�ק�K�X����)
			System.out.println();
			return false;
		}
		
		// ���
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// ��x
				System.out.println("LoginInterceptor.postHandle()");
				System.out.println("\t request: " + request);
				System.out.println("\t response: " + response);
				System.out.println("\t Object handler: " + handler);
				System.out.println("\t ModelAndView modelAndView: " + modelAndView);
				
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		// ��x
				System.out.println("LoginInterceptor.afterCompletion()");
				System.out.println("\t request: " + request);
				System.out.println("\t response: " + response);
				System.out.println("\t Object handler: " + handler);
				System.out.println("\t Exception ex: " + ex);
	}

}
