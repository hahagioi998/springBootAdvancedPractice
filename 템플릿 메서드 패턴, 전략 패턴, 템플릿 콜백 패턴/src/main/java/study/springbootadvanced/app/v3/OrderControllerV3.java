package study.springbootadvanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.springbootadvanced.trace.TraceStatus;
import study.springbootadvanced.trace.hellotrace.HelloTraceV2;
import study.springbootadvanced.trace.logtrace.FieldLogTrace;
import study.springbootadvanced.trace.logtrace.LogTrace;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    /**
     * HelloTraceV1을 적용
     */

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId){

        TraceStatus status = null;

        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        }catch (Exception e){
            trace.exception(status, e);
            // 예외를 꼭 다시 던져주어야 한다.
            // 던지지 않을시 trace.exception()에서 exception을 먹어 버리고 정상 흐름이 되기 때문.
            throw e;
        }
    }
}
