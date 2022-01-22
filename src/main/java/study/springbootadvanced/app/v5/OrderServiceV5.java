package study.springbootadvanced.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.springbootadvanced.trace.callback.TraceCallback;
import study.springbootadvanced.trace.callback.TraceTemplate;
import study.springbootadvanced.trace.logtrace.LogTrace;
import study.springbootadvanced.trace.template.AbstractTemplate;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId){
        template.execute("OrderService.OrderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
