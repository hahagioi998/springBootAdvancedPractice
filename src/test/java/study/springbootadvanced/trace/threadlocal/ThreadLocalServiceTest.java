package study.springbootadvanced.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import study.springbootadvanced.trace.threadlocal.code.FieldService;
import study.springbootadvanced.trace.threadlocal.code.ThreadLocalService;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService fieldService = new ThreadLocalService();

    @Test
    void field(){
        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        /**
         * threadA는 userA로직을 실행
         * threadB는 userB로직을 실행
         */
        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        /**
         * 2초 쉼
         * 동시성 문제가 발생 X
         */
//        sleep(2000);

        /**
         * 1. fieldService.logic내의 1초(1000 milis) 보다 더 적은 100 millis의 시간의 할당
         * 2. ThreadLocal 덕분에 쓰레드 마다 별도의 데이터 저장소를 가져 동시성 문제 해결
         * 3. 동시성 문제 발생  X
         */
        sleep(100);

        threadB.start();

        sleep(3000); //메인 쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
