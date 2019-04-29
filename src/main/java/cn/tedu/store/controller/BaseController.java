package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

public abstract class BaseController {

	/**
	 * 從Session中獲取當前登錄的用戶ID
	 * @param session
	 * @return 如果Session存在用戶ID, 則返回用戶ID, 否則返回null
	 */
	protected Integer getUidFromSession(HttpSession session) {
		Object uidObject = session.getAttribute("uid");
		if(uidObject == null) {
			return null;
			
		} else {
			return Integer.valueOf(uidObject.toString());
			
		}
	}
}
