package foo.criteria.specificmerchant.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resource
import spock.lang.Unroll

import foo.criteria.specificmerchant.SpecificMerchantCriterion

import static SpecificMerchantCriterionLinks.EXPORT_CSV_TEMPLATE_REL

class SpecificMerchantCriterionPagedResourcesProcessorSpec extends SpecificMerchantCriterionResourceProcessorSpec {

    @Autowired
    SpecificMerchantCriterionPagedResourcesProcessor specificMerchantCriterionPagedResourcesProcessor

    def "Empty SpecificMerchantCriterion response"() {

        when: "Empty SpecificMerchantCriterion list is processed"
        PagedResources<Resource<SpecificMerchantCriterion>> pagedResources = new PagedResources<>(new ArrayList<>(), new PagedResources.PageMetadata(0, 0, 0))
        PagedResources<Resource<SpecificMerchantCriterion>> resourcesEnriched = specificMerchantCriterionPagedResourcesProcessor.process(pagedResources)

        then:
        resourcesEnriched.links.size() == 1
        resourcesEnriched.getLink(EXPORT_CSV_TEMPLATE_REL) != null
    }

    @Unroll
    def "#testSubject with Specification in #spec.status status and validity #specDescription and #specificData.size() params"() {
        expect: "Generic and specific test data are 'merged' and thus must be of same size"
        genericTestData().size() == specificCriterionData().size()

        when: "Get links from resource processor"
        def criterion = (SpecificMerchantCriterion) createSpecificCriterion((List<Object>) specificData)
        Collection<Resource<SpecificMerchantCriterion>> resourceList = Arrays.asList(new Resource<>(criterion))
        PagedResources<Resource<SpecificMerchantCriterion>> pagedResources = new PagedResources<Resource<SpecificMerchantCriterion>>(
                resourceList,
                new PagedResources.PageMetadata(1000, 0, resourceList.size()))


        PagedResources<Resource<SpecificMerchantCriterion>> resourcesEnriched = specificMerchantCriterionPagedResourcesProcessor.process(pagedResources)

        then: "All and only expected links are present"
        resourcesEnriched.links.size() == 1
        resourcesEnriched.getLink(EXPORT_CSV_TEMPLATE_REL) != null

        where:
        testSubject = this.class.simpleName.reverse().replaceFirst("Spec".reverse(), "").reverse()
        [spec, specDescription, expectedLinks] << genericTestData()
        [specificData] << specificCriterionData()
    }
}
