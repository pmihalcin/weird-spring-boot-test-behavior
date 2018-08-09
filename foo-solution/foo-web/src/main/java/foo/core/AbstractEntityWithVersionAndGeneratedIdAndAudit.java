package foo.core;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Base class for entities which adds versioning, generated id and auditing
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class AbstractEntityWithVersionAndGeneratedIdAndAudit<ID extends Serializable> extends AbstractEntityWithVersionAndGeneratedId<ID> {

    @JsonIgnore
    @Column(name = "CREATED_BY", updatable = false, nullable = false)
    @CreatedBy
    private String createdBy;

    @JsonIgnore
    @Column(name = "CREATED_TS", updatable = false, columnDefinition = "TIMESTAMP", nullable = false)
    @DateTimeFormat(iso = DATE_TIME)
    @CreatedDate
    private LocalDateTime createdDate;

    @JsonIgnore
    @Column(name = "MODIFIED_BY")
    @LastModifiedBy
    private String lastModifiedBy;

    @JsonIgnore
    @Column(name = "MODIFIED_TS", columnDefinition = "TIMESTAMP")
    @DateTimeFormat(iso = DATE_TIME)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    protected AbstractEntityWithVersionAndGeneratedIdAndAudit() {
        super();
    }
}
