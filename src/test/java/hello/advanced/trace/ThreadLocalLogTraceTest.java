package hello.advanced.trace;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.logtrace.ThreadLocalLogTrace;

class ThreadLocalLogTraceTest {

    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    void begin_end_level2() {
        final TraceStatus status1 = trace.begin("hello1");
        final TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception_level2() {
        final TraceStatus status1 = trace.begin("hello1");
        final TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

}
