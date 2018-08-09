package foo.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.util.Assert;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal.Franek
 */
@MappedSuperclass
public class AbstractEntityWithVersionAndGeneratedIdAndAuditAndEventAware<ID extends Serializable> extends AbstractEntityWithVersionAndGeneratedIdAndAudit<ID> {

    /**
     * All domain events currently captured by the aggregate.
     */
    @JsonIgnore
    @Transient
    @Getter(onMethod = @__(@DomainEvents))
    private final List<Object> domainEvents = new ArrayList<>();

    /**
     * Registers the given event object for publication on a call to a Spring Data repository's save method.
     *
     * @param event must not be {@literal null}.
     * @return T
     */
    protected <T> T registerEvent(T event) {

        Assert.notNull(event, "Domain event must not be null!");

        this.domainEvents.add(event);
        return event;
    }

    /**
     * Clears all domain events currently held. Usually invoked by the infrastructure in place in Spring Data
     * repositories.
     */
    @AfterDomainEventPublication
    public void clearDomainEvents() {
        this.domainEvents.clear();
    }
}
