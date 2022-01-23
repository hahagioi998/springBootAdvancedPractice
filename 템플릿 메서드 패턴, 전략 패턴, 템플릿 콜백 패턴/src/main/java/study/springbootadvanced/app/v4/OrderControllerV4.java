package study.springbootadvanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.springbootadvanced.trace.TraceStatus;
import study.springbootadvanced.trace.logtrace.LogTrace;
import study.springbootadvanced.trace.template.AbstractTemplate;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
    /**
     * HelloTraceV1을 적용
     */

    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/request")
    public String request(String itemId) {


        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
        return template.execute("OrderController.request()");
    }
}
