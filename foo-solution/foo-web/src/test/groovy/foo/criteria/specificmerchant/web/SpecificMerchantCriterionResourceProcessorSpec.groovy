package foo.criteria.specificmerchant.web

import org.springframework.beans.factory.annotation.Autowired
import foo.criteria.AbstractCriterionResourceProcessorSpec
import foo.criteria.Criterion
import foo.criteria.specificmerchant.SpecificMerchant
import foo.criteria.specificmerchant.SpecificMerchantCriterion

class SpecificMerchantCriterionResourceProcessorSpec extends AbstractCriterionResourceProcessorSpec {

    @Autowired
    SpecificMerchantCriterionResourceProcessor specificMerchantCriterionResourceProcessor

    @Override
    List<List<Object>> specificCriterionData() {
        [
                [[[merchantId: "C1", acquirerId: "A1", terminalId: "T1"], [merchantId: "C2", acquirerId: "A2", terminalId: "T2"]]],
        ]
    }

    @Override
    Criterion createSpecificCriterion(List<Object> specificData) {
        createSpecificMerchantCriterion(specificData)
    }

    static SpecificMerchantCriterion createSpecificMerchantCriterion(List<Map> specificData) {
        SpecificMerchantCriterion criterion
        if (specificData.size() > 0) {
            List<SpecificMerchant> specificMerchants = []
            specificData.each {
                c -> specificMerchants.add(SpecificMerchant.of((String) c.merchantId, (String) c.acquirerId, (String) c.terminalId))
            }
            //noinspection GroovyAssignabilityCheck
            criterion = new SpecificMerchantCriterion(*specificMerchants)
        } else {
            criterion = new SpecificMerchantCriterion()
        }
        return criterion
    }

    @Override
    def genericTestData() {
        def data = super.genericTestData()
        data
    }
}
