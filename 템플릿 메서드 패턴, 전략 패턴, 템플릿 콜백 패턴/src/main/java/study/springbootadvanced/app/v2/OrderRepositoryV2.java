package study.springbootadvanced.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.springbootadvanced.trace.TraceId;
import study.springbootadvanced.trace.TraceStatus;
import study.springbootadvanced.trace.hellotrace.HelloTraceV1;
import study.springbootadvanced.trace.hellotrace.HelloTraceV2;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId){

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderRepository.save()");

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
