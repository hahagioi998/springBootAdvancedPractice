package study.springbootadvanced.trace.strategy;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import study.springbootadvanced.trace.strategy.code.strategy.*;

@Slf4j
public class ContextV2Test {

    /**
     * 전략 패턴 적용
     * 의존관계가 아닌 파라미터로 전달
     */
    @Test
    void strategyV1(){
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    /**
     * 전략 패턴 적용 - 익명 내부 클래스 (의존관계가 아닌 파라미터로 전달)
     */
    @Test
    void strategyV2(){
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }


    /**
     * 전략 패턴 적용 - 람다 (의존관계가 아닌 파라미터로 전달)
     */
    @Test
    void strategyV3(){
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비즈니스 로직1 실행"));
        context.execute(() -> log.info("비즈니스 로직2 실행"));
    }

}
