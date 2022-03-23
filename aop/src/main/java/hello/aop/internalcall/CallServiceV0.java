package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 기존의 프록시방식의 AOP의 내부 호출 문제점 발생-(내부의 메서드 호출시 AOP를 거치지 않고 바로 호출 됨)->
 * 버전 up 되면서 프록시 방식의 AOP에서 내부 호출에 대응할수 있는 방법들 설명
 */
@Slf4j
@Component
public class CallServiceV0 {

    public void external(){
        log.info("call external");
        internal();
    }

    public void internal() {
        log.info("call internal");
    }
}
