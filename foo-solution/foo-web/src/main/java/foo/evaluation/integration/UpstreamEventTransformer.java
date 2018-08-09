package foo.evaluation.integration;

import foo.evaluation.UpstreamEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpstreamEventTransformer {

    public EvaluationLUW transform(String content) {
        UpstreamEvent event = new UpstreamEvent();
        event.setContent(content);
        return new EvaluationLUW(content, event);
    }
}
