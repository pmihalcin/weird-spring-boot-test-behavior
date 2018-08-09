package foo.evaluation.integration;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EvaluationHandler implements GenericHandler<EvaluationLUW> {

    @Override
    public Object handle(EvaluationLUW luw, Map<String, Object> headers) {
        return luw.getEnvelope();
    }

}
