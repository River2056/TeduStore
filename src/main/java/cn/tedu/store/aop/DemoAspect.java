package cn.tedu.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 切面組件
 * @author User
 *
 */
@Component
@Aspect // 來自AspectJ的註解
public class DemoAspect {
	// 來自AspectJ的註解
	// 在userService的全部方法之前執行
	// 切面組件的demo方法
	@Before("bean(userService)")
	public void demo() {
		System.out.println("cn.tedu.store.aop.DemoAspect.demo() -> Hello World!");
	}
	
	// 在目標方法之後執行
	@After("bean(userService)")
	public void demoAfter() {
		System.out.println("cn.tedu.store.aop.DemoAspect.demoAfter() -> After demoAfter");
	}
	
	// 在目標方法沒有異常以後執行
	@AfterReturning("bean(userService)")
	public void demoAfterReturning() {
		System.out.println("cn.tedu.store.aop.DemoAspect.demoAfterReturning() -> AfterReturning");
	}
	
	// 在目標方法出現異常以後執行
	@AfterThrowing("bean(userService)")
	public void demoAfterThrowing() {
		System.out.println("cn.tedu.store.aop.DemoAspect.demoAfterThrowing() -> AfterThrowing");
	}
	
	// 環繞通知:
	// 1. 方法必須有返回值
	// 2. 方法必須有參數ProceedingJoinPoint
	//		處理連接點
	// 3. 在方法中調用 pjp.proceed() 就是執行目標的業務方法, 返回值就是目標方法的執行結果
	// 4. pjp.proceed() 方法執行出現異常, 就是目標業務方法執行出現異常
	@Around("bean(userService)")
	public Object demoAround(ProceedingJoinPoint pjp) throws Throwable {
		// 測試業務層運行時間
		System.out.println("開始測試...");
		long start = System.currentTimeMillis();
		Object val = pjp.proceed();
		long end = System.currentTimeMillis();
		System.out.println(val);
		long result = end - start;
		System.out.println("結束, 經過時間: " + result);
		return val;
	}
	
}
