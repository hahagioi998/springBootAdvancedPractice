package study.springbootadvanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.springbootadvanced.trace.TraceStatus;
import study.springbootadvanced.trace.logtrace.LogTrace;
import study.springbootadvanced.trace.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };
        template.execute("OrderService.OrderItem()");
    }
}
