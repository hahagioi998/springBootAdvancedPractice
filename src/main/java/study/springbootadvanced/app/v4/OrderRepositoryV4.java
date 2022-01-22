package study.springbootadvanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.springbootadvanced.trace.TraceStatus;
import study.springbootadvanced.trace.logtrace.LogTrace;
import study.springbootadvanced.trace.template.AbstractTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                //저장 로직
                if (itemId.equals("ex")) {
                    throw new IllegalArgumentException("예외 발생!");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.save()");
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
