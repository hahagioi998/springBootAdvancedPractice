package study.springbootadvanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.springbootadvanced.trace.TraceId;
import study.springbootadvanced.trace.TraceStatus;
import study.springbootadvanced.trace.hellotrace.HelloTraceV2;
import study.springbootadvanced.trace.logtrace.LogTrace;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId){

        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");

            //저장 로직
            if (itemId.equals("ex")){
                throw new IllegalArgumentException("예외 발생!");
            }
            sleep(1000);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
