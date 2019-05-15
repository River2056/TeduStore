package cn.tedu.store.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 切入點表達式演示
 * @author User
 *
 */
@Component
@Aspect
public class PointCutAspect {

	@Before("within(cn.tedu.store..*Impl)")
	public void testUsingWithin() {
		System.out.println("Point Cut 1");
	}
	
	@Before("execution(* cn.tedu.store..IUserService.login(..))")
	public void testUsingExecution() {
		System.out.println("Point Cut 2");
	}
}
