package hello.proxy;

import hello.proxy.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import hello.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(AppV1Config.class)
//@Import({AppV1Config.class,AppV2Config.class})
//@Import(InterfaceProxyConfig.class) // interface 에 직접프록시로 LogTrace 적용
//@Import(ConcreteProxyConfig.class) // 구현체에 직접프록시로 LogTrace 적용
//@Import(DynamicProxyBasicConfig.class) // 모든 매서드에 동적프록시로 LogTrace 적용
@Import(DynamicProxyFilterConfig.class)// 패턴이 일치하는 모든 매서드에 동적프록시로 LogTrace 적용(no-log제외)
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace(){
		return new ThreadLocalLogTrace();
	}
}
