package foo.core.web.exception;

import lombok.Getter;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

@Getter
public class RelatedEntitiesMissingException extends RuntimeException {

    private final transient Object entity;
    private final JpaObjectRetrievalFailureException cause;

    public RelatedEntitiesMissingException(Object entity, String message) {
        super(message);
        this.entity = entity;
        this.cause = null;
    }
}
