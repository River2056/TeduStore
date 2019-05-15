package cn.tedu.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {

	@Around("bean(*Service)")
	public Object testPerformance(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("===== 統一測試性能 =====");
		long t1 = System.currentTimeMillis();
		Object val = pjp.proceed();
		long t2 = System.currentTimeMillis();
		Signature s = pjp.getSignature();
		System.out.println(s + "耗時: " + (t2 - t1));
		System.out.println("===== 測試結束 =====");
		return val;
	}
}
