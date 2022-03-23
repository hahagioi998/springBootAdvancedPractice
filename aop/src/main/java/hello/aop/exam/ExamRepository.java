package hello.aop.exam;

import hello.aop.exam.annotation.Retry;
import hello.aop.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

    private static int seq = 0;

    /**
     * 3번에 1번 실패하는 요청 - 시스템에서 간헐적 에러가 뜨는 경우를 상정
     */
    @Trace
    @Retry(value = 4)
    public String save(String itemId){
        seq++;
        if(seq % 3 == 0){
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }
}
