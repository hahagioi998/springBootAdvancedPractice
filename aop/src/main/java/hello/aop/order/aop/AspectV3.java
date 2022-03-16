package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Around 에 포인트컷 표현식을 직접 넣을 수 도 있지만,
 * @Pointcut 애노테이션을 사용해서 별도로 분리
 */
@Slf4j
@Aspect
public class AspectV3 {

    // hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))") //pointcut expression
    private void allOrder(){} // pointcut signature

    // 클래스 이름 패턴이 *Service - 비즈니스 로직에 크랜젹션 검
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService(){}


    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws  Throwable{
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
        return joinPoint.proceed();
    }

    // hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
    @Around("allOrder() && allService()")
    private Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{

        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        }catch (Exception e){
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        }finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
}
