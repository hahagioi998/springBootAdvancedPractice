package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    /**
     * hello.proxy.config.v3_proxyfactory.advice.LOgTraceAdvice.class 의 로직 복붙;
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* hello.proxy.app..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{

        TraceStatus status = null;

        try{
//            joinPoint.getTarget(); 실제 호출 대상
//            joinPoint.getArgs() 전달 인자
            // join point 시그니처
            String message = joinPoint.getSignature().toShortString();

            status = logTrace.begin(message);

            //로직 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
