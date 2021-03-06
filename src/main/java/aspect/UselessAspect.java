package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/4/5
 * Time: 14:41
 * Description:
 */
@Component()
@Aspect
public class UselessAspect {

    @Pointcut("within(service.impl.UselessServiceImpl)")
    public void logger() {
    }

    @Before("logger()")
    public void before(JoinPoint joinPoint) {
        Class<?> aClass = joinPoint.getTarget().getClass();
        System.out.println(aClass);
        System.out.println("do something before");
    }

    @After("logger()")
    public void after() {
        System.out.println("do something after");
    }
}
