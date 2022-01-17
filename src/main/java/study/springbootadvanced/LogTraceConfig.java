package study.springbootadvanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.springbootadvanced.trace.logtrace.FieldLogTrace;
import study.springbootadvanced.trace.logtrace.LogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
        return new FieldLogTrace();
    }
}
