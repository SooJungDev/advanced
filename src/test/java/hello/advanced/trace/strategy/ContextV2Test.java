package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV2;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class ContextV2Test {

    /**
     * 전략 패턴 적용
     * Context를 실행할때마다 전달을 인수로 전달한다.
     * 클라이언트는 Context를 실행하는 시점에 원하는 Strategy 를 전달 할 수 있다.
     * 따라서 이전 방식과 비교해서 원하는 전략을 더욱 유연하게 변경 할 수 있다.
     * 하나의 Context 에 실행시점에 여러전략을 인수로 전달해서 유연하게 실행하는 것을 확인 할 수 있다.
     * 단점 역시 실행할때마다 전략을 계속 지정해줘야한다는 점
     */
    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }


    /**
     * 전략 패턴 익명 내부 클래스
     */
    @Test
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
    }

    /**
     * 전략 패턴 익명 내부 클래스2, 람다
     */
    @Test
    void strategyV3() {
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비지니스 로직1 실행"));
        context.execute(() -> log.info("비지니스 로직2 실행"));
    }

}
