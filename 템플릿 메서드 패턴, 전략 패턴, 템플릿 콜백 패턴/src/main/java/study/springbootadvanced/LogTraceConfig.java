package study.springbootadvanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.springbootadvanced.trace.logtrace.FieldLogTrace;
import study.springbootadvanced.trace.logtrace.LogTrace;
import study.springbootadvanced.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
//        return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }
}
