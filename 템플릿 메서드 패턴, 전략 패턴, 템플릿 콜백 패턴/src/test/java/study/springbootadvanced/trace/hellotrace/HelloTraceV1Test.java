package study.springbootadvanced.trace.hellotrace;

import org.junit.jupiter.api.Test;
import study.springbootadvanced.trace.TraceStatus;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV1Test {

    @Test
    void begin_end(){
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus status = helloTraceV1.begin("hello");
        helloTraceV1.end(status);
    }

    @Test
    void begin_exception(){
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus status = helloTraceV1.begin("hello");
        helloTraceV1.exception(status, new IllegalArgumentException());
    }
}