package foo.criteria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by Patrik.Mihalcin on 27.2.2017.
 */
@Repository
@RepositoryRestResource(path = "criteria", collectionResourceRel = "criteria", itemResourceRel = "criterion")
public interface CriterionRepository extends JpaRepository<Criterion, Long> {

}
