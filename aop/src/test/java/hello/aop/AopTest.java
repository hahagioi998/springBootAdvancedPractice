package hello.aop;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import hello.aop.order.aop.AspectV1;
import hello.aop.order.aop.AspectV3;
import hello.aop.order.aop.AspectV4.AspectV4Pointcut;
import hello.aop.order.aop.AspectV5.AspectV5Order;
import hello.aop.order.aop.AspectV6.AspectV6Advice;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
//@Import(AspectV1.class) // @Around
//@Import(AspectV2.class) // @Pointcut
//@Import(AspectV3.class) // 어드바이저 추가
//@Import(AspectV4Pointcut.class) // 포인트컷
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class}) // 어드바이스 순서
@Import(AspectV6Advice.class) // 어드바이스 종류
public class AopTest {


    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo(){
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success(){
        orderService.orderItem("itemA");
    }

    @Test
    void exception(){
        Assertions.assertThatThrownBy(() -> orderService.orderItem("ex")).isInstanceOf(IllegalArgumentException.class);
    }
}
