package foo.criteria;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import foo.core.AbstractEntityWithVersionAndGeneratedIdAndAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Base class for criterion implementations
 * <p>
 * Created by patrik.mihalcin on 3.4.2017.
 */
@ToString(callSuper = true)
@Getter
@SequenceGenerator(name = "SEQ", sequenceName = "SQ_MER_CRITERION", allocationSize = 1)
@DiscriminatorColumn(name = "CRITERION_TYPE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "MER_CRITERION")
@Entity
public abstract class Criterion extends AbstractEntityWithVersionAndGeneratedIdAndAudit<Long> {

    @Setter
    @Column(name = "SHOULD_MATCH")
    private boolean shouldMatch;

    public Criterion() {
    }

    public Criterion(boolean shouldMatch) {
        this.shouldMatch = shouldMatch;
    }
}
