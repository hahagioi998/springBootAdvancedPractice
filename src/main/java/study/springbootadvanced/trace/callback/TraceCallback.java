package study.springbootadvanced.trace.callback;

public interface TraceCallback<T> {
    T call();
}
