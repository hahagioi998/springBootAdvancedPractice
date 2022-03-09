package hello.proxy;

import hello.proxy.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import hello.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import hello.proxy.config.v3_proxyfactory.ProxyFactoryConfigV1;
import hello.proxy.config.v3_proxyfactory.ProxyFactoryConfigV2;
import hello.proxy.config.v4_postprocessor.BeanPostProcessorConfig;
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
//@Import(DynamicProxyFilterConfig.class)// 패턴이 일치하는 모든 매서드에 동적프록시로 LogTrace 적용(no-log제외)
//@Import(ProxyFactoryConfigV1.class) // 인터페이스 구조 어드바이스
//@Import(ProxyFactoryConfigV2.class) //구체클래스 구조 어드바이스
@Import(BeanPostProcessorConfig.class) // 빈후처리기 적용한 어드바이스
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
