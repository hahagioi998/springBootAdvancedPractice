package study.springbootadvanced.trace.strategy;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import study.springbootadvanced.trace.strategy.code.strategy.ContextV1;
import study.springbootadvanced.trace.strategy.code.strategy.Strategy;
import study.springbootadvanced.trace.strategy.code.strategy.StrategyLogic1;
import study.springbootadvanced.trace.strategy.code.strategy.StrategyLogic2;
import study.springbootadvanced.trace.template.code.AbstractTemplate;
import study.springbootadvanced.trace.template.code.SubClassLogic1;
import study.springbootadvanced.trace.template.code.SubClassLogic2;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0(){
        logic1();
        logic2();
    }

    /**
     * 시간을 측정하는 로직과 핵심로직이 섞여있음
     */
    @Test
    private void logic1(){
        long startTime = System.currentTimeMillis();

        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    private void logic2(){
        long startTime = System.currentTimeMillis();

        //비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }


    /**
     *  전략 패턴 사용
     */
    @Test
    void strategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    /**
     * 전략패턴 - 익명 클래스
     */
    @Test
    void strategyV2(){
        Strategy strategyLogic1 = new Strategy(){
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        };

        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        log.info("StrategyLogic1={}", strategyLogic1.getClass());
        contextV1.execute();


        Strategy strategyLogic2 = new Strategy(){
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        };

        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        log.info("StrategyLogic2={}", strategyLogic2.getClass());
        contextV2.execute();
    }

    /**
     * 전략패턴 - 익명 클래스(단축)
     */
    @Test
    void strategyV3() {

        ContextV1 contextV1 = new ContextV1(new Strategy(){
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(new Strategy(){
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
        contextV2.execute();
    }

    /**
     * 전략패턴 - 람다
     */
    @Test
    void strategyV4() {
        ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
        contextV2.execute();
    }
}
