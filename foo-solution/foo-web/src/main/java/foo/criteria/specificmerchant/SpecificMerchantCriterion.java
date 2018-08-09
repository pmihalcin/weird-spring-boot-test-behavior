package foo.criteria.specificmerchant;

import static java.util.Arrays.asList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import foo.core.web.exception.RelatedEntitiesMissingException;
import foo.criteria.Criterion;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * {@link Criterion} represented by 3-tuple (merchant ID, acquirer ID, terminal ID).
 * <p>
 * Created by patrik.mihalcin on 3.4.2017.
 */
@Getter
@RestResource(rel = "specificMerchantCriteria", path = "specificMerchantCriteria")
@Entity
@ToString(callSuper = true, exclude = {"specificMerchants"})
public class SpecificMerchantCriterion extends Criterion {

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "MER_SPECIFIC_MERC_CRIT_VALUE", joinColumns = @JoinColumn(name = "CRITERION_ID"))
    @Column(nullable = false, unique = true, updatable = false)
    private List<SpecificMerchant> specificMerchants = new LinkedList<>();

    public SpecificMerchantCriterion() {
    }

    public SpecificMerchantCriterion(List<SpecificMerchant> specificMerchants) {
        this.specificMerchants.addAll(specificMerchants);
    }

    public SpecificMerchantCriterion(SpecificMerchant... specificMerchants) {
        this(asList(specificMerchants));
    }

    public SpecificMerchantCriterion(boolean shouldMatch, List<SpecificMerchant> specificMerchants) {
        super(shouldMatch);

        if (specificMerchants.isEmpty()) {
            throw new RelatedEntitiesMissingException(this,
                    "No specific merchants associated with specific merchant criterion");
        }
        this.specificMerchants.addAll(specificMerchants);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificMerchantCriterion that = (SpecificMerchantCriterion) o;
        return Objects.equals(specificMerchants, that.specificMerchants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specificMerchants);
    }
}
