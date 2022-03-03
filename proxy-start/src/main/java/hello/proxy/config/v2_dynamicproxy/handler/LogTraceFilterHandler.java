package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * no-log 의  로그를 남기지 않기 위한 Handler
 */
public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;
    private final String[] patterns; // 메서드 명이 해당 pattern 일때만 로그를 남김.

    public LogTraceFilterHandler(Object target, LogTrace logTrace, String... pattern) {
        this.target = target;
        this.logTrace = logTrace;
        this.patterns = pattern;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 메서드 이름 필터
        String methodName = method.getName();
        // save, request, reque*, *est -> 패턴 적용
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)){
            return method.invoke(target, args);
        }


        TraceStatus status = null;

        try {
            String message = method.getDeclaringClass().getSimpleName() + ","
                    + method.getName() + "()";
            status = logTrace.begin(message);

            //target 로직 호출
            Object result = method.invoke(target, args);


            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
