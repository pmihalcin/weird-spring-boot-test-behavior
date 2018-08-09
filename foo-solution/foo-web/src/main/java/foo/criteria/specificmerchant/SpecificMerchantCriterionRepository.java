package foo.criteria.specificmerchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Patrik.Mihalcin on 27.2.2017.
 */
@RepositoryRestResource(path = "specificMerchantCriteria", collectionResourceRel = "specificMerchantCriteria", itemResourceRel = "specificMerchantCriterion")
public interface SpecificMerchantCriterionRepository extends JpaRepository<SpecificMerchantCriterion, Long>, QueryDslPredicateExecutor<SpecificMerchantCriterion> {
}
