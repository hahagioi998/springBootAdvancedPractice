package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

@Slf4j
public class ArgsTest {

    Method helloMethod; //메타정보


    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    private AspectJExpressionPointcut pointcut(String expression){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @Test
    void args(){
        //hello(String)과 매칭
        Assertions.assertThat(pointcut("args(String)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        Assertions.assertThat(pointcut("args(Object)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        Assertions.assertThat(pointcut("args()").matches(helloMethod, MemberServiceImpl.class)).isFalse();
        Assertions.assertThat(pointcut("args(..)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        Assertions.assertThat(pointcut("args(*)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        Assertions.assertThat(pointcut("args(String,..)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    @Test
    void argsVsExecution(){
        //Args
        Assertions.assertThat(pointcut("args(String)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        Assertions.assertThat(pointcut("args(java.io.Serializable)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        Assertions.assertThat(pointcut("args(Object)").matches(helloMethod, MemberServiceImpl.class)).isTrue();


        //Execution
        Assertions.assertThat(pointcut("execution(* *(String))").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        Assertions.assertThat(pointcut("execution(* *(java.io.Serializable))").matches(helloMethod, MemberServiceImpl.class)).isFalse();
        Assertions.assertThat(pointcut("execution(* *(Object))").matches(helloMethod, MemberServiceImpl.class)).isFalse();

    }
}
