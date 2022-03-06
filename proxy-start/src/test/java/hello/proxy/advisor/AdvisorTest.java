package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
public class AdvisorTest {

    @Test
    @DisplayName("어드바이저를 제공하면 어디에 어떤 기능을 제공할지 알 수 있다.")
    void advisorTest1(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);


        // PointCut.TRUE = 항상 TRUE를 반환하는 포인트 컷
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());//어드바이저 추가
        proxyFactory.addAdvisor(advisor);

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.save();
        proxy.find();

        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }
}
