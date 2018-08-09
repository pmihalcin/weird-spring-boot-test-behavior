package foo.evaluation

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.dao.DataIntegrityViolationException

/**
 * Created by Patrik.Mihalcin on 12.06.2018
 */
@Aspect
class FailureSimulator {

    @Before("execution(* *..*EvaluationHandler+.handle(..))")
    static void fail() {
        simulateBusinessProcessingFailure();
    }

    private static void simulateBusinessProcessingFailure() {
        throw new DataIntegrityViolationException("Simulated failure.");
    }

}
