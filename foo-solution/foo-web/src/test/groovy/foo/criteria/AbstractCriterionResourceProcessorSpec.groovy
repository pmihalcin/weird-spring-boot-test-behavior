package foo.criteria

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

import static foo.criteria.specificmerchant.web.SpecificMerchantCriterionLinks.EXPORT_SPECIFIC_MERCHANT_REL

@Unroll
@SpringBootTest
abstract class AbstractCriterionResourceProcessorSpec extends Specification {

    public static final REL_CRITERION_DELETE = "delete"
    public static final REL_CRITERION_EDIT = "edit"

    def "#testSubject with Specification in #spec.status status and validity #specDescription and #specificData.size() params"() {
        true

        where:
        testSubject = this.class.simpleName.reverse().replaceFirst("Spec".reverse(), "").reverse()
        [expectedLinks] << genericTestData()
        [specificData] << specificCriterionData()
    }

    def genericTestData() {
        def data = [
                [[REL_CRITERION_EDIT, REL_CRITERION_DELETE, EXPORT_SPECIFIC_MERCHANT_REL]],
        ]
        data
    }

    abstract List<List<Object>> specificCriterionData()

    abstract Criterion createSpecificCriterion(List<Object> specificData)
}
