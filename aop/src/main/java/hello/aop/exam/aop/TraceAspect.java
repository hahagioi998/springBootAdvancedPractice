package hello.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class TraceAspect {

    @Before("@annotation(hello.aop.exam.annotation.Trace)")
    public void doTest(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        log.info("[target] {} args={}", joinPoint.getSignature(),args);
    }
}
