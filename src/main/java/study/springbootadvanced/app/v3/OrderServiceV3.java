package study.springbootadvanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.springbootadvanced.trace.TraceId;
import study.springbootadvanced.trace.TraceStatus;
import study.springbootadvanced.trace.hellotrace.HelloTraceV2;
import study.springbootadvanced.trace.logtrace.LogTrace;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){

        TraceStatus status = null;
        try {
             status = trace.begin("OrderService.OrderItem()");
             orderRepository.save(itemId);
             trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
