/*
 * Created by tomas.priecinsky on 22.9.2017.
 */
package foo.criteria.specificmerchant.web;

import foo.criteria.specificmerchant.SpecificMerchantCriterion;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@ConditionalOnWebApplication
@Component
@RequiredArgsConstructor
public class SpecificMerchantCriterionResourceProcessor implements ResourceProcessor<Resource<SpecificMerchantCriterion>> {

    @NonNull
    private final SpecificMerchantCriterionLinks specificMerchantCriterionLinks;

    @Override
    public Resource<SpecificMerchantCriterion> process(Resource<SpecificMerchantCriterion> resource) {
        SpecificMerchantCriterion criterion = resource.getContent();

        resource.add(
                specificMerchantCriterionLinks.getEditLink(criterion),
                specificMerchantCriterionLinks.getDeleteLink(criterion)
        );

        resource.add(specificMerchantCriterionLinks.getExportSpecificMerchantLink(criterion));

        return resource;
    }
}
