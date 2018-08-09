package foo.core;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

/**
 * Base class for entity implementations.
 * <p>
 * Created by patrik.mihalcin on 28.2.2017.
 */
@MappedSuperclass
@Getter
@ToString
public class AbstractEntityWithVersionAndGeneratedId<ID extends Serializable> implements Identifiable<ID> {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ")
    private final ID id;

    @Version
    @Column(name = "REC_VERSION", nullable = false)
    private Long version;

    protected AbstractEntityWithVersionAndGeneratedId() {
        this.id = null;
    }
}
