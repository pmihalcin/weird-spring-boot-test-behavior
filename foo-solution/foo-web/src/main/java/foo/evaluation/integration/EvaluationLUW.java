package foo.evaluation.integration;

import foo.evaluation.UpstreamEvent;

import lombok.Data;

@Data
class EvaluationLUW {

    private final Object envelope;
    private final UpstreamEvent event;

}
