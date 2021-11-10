package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 선 조립, 후 실행
 * Context 내부 필드에 Strategy 를 두고 사용하는 부분
 * 이 방식의 단점은 context 와 strategy 를 조립한 이후에는 전략을 변경하기가 번거롭다는 점이다
 * Context 를 싱글톤으로 사용할때 동시성 이슈등 고려할 점이 많다.
 * 그래서 전략을 실시간으로 변경해야 하면 차라리 이전에 개발한 테스트 코드처럼 Context 를 하나더 생성하고
 * 그곳에 다른 Strategy 를 주입하는것이 더 나은 선택일 수 있다.
 */
@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        // 비지니스 로직 실행
        log.info("비지니스 로직1 실행");
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        System.out.println("resultTime = " + resultTime);
    }


    private void logic2() {
        long startTime = System.currentTimeMillis();
        // 비지니스 로직 실행
        log.info("비지니스 로직2 실행");
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        System.out.println("resultTime = " + resultTime);
    }

    /**
     * 전략 패턴 사용
     */
    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();

    }

    @Test
    void strategyV2() {
        Strategy strategyLogic1 = new Strategy() {

            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };

        ContextV1 context1 = new ContextV1(strategyLogic1);
        log.info("strategyLogic1={}", strategyLogic1);
        context1.execute();

        Strategy strategyLogic2 = new Strategy() {

            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        };

        ContextV1 context2 = new ContextV1(strategyLogic2);
        log.info("strategyLogic2={}", strategyLogic2);
        context2.execute();
    }

    /**
     * 익명 내부 클래스
     */
    @Test
    void strategyV3() {
        ContextV1 context1 = new ContextV1(new Strategy() {

            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {

            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });

        context2.execute();
    }

    /**
     * 람다로 하려면 인터페이스에 메서드가1개만 있으면 됨
     */
    @Test
    void strategyV4() {
        ContextV1 context1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
        context1.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("비지니스 로직2 실행"));
        context2.execute();
    }
}
