package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    /**
     * 구체 클래스를 기반으로 cglib 구현
     */
    @Test
    void cglib(){
        ConcreteService target = new ConcreteService();


        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));


        ConcreteService proxy = (ConcreteService) enhancer.create();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }
}
