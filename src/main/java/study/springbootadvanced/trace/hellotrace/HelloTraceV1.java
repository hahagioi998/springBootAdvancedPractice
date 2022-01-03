package study.springbootadvanced.trace.hellotrace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.springbootadvanced.trace.TraceId;
import study.springbootadvanced.trace.TraceStatus;

@Slf4j
@Component
public class HelloTraceV1 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<x-";

    /**
     * 로그 시작시 로그 메시지를 파라미터로 받아서 시작 로그를 출력
     * @param message
     * @return
     */
    public TraceStatus begin(String message){
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    /**
     * 로그를 정상 종료한다. 실행시간을 계산하고, 종료시에도 시작할때와 동일한 로그 메시지를 출력할 수 있다.
     * @param status
     */
    public void end(TraceStatus status){
        complete(status, null);
    }

    /**
     * 로그를 예외 상황으로 종료한다. 실행시간, 예외정보를 포함한 결과 로그를 출력한다.
     * @param status
     * @param e
     */
    public void exception(TraceStatus status, Exception e){
        complete(status, e);
    }

    /**
     * end(), exception() 의 요청 흐름을 한곳에서 편리하게 처리한다.
     * @param status
     * @param e
     */
    private void complete(TraceStatus status, Exception e){
        long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null){
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        }else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }
    }

    /**
     * 메서드의 depth를 string으로 보여준다. <--, -->, <X-
     * level = 0
     * level = 1 |->
     * level = 2 |  |->
     * @param prefix
     * @param level
     * @return
     */
    private static String addSpace(String prefix, int level){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <level; i++){
            sb.append((i == level - 1) ? "|" + prefix : "|  ");
        }
        return sb.toString();
    }
}
