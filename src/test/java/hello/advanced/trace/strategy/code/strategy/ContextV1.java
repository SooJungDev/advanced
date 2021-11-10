package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 * 쉽게 말해서 컨텍스트(문맥)은 크게 변하지 않지만 그 문맥속에서 strategy 를 통해 일부 전략이 변경된다 생각하면됨
 * 스프링에서 의존관계 주입에서 사용하는 방식이 전략 패턴이다.
 */
@Slf4j
public class ContextV1 {
    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(){
        long startTime = System.currentTimeMillis();
        // 비지니스 로직 실행
        strategy.call(); // 위임
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        System.out.println("resultTime = " + resultTime);
    }
}
