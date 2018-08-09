package foo.criteria.specificmerchant.web;

import foo.criteria.specificmerchant.SpecificMerchantCriterion;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

@ConditionalOnWebApplication
@Component
@RequiredArgsConstructor
public class SpecificMerchantCriterionResourcesProcessor implements ResourceProcessor<Resources<SpecificMerchantCriterion>> {

    @NonNull
    private final SpecificMerchantCriterionLinks specificMerchantCriterionLinks;

    @Override
    public Resources<SpecificMerchantCriterion> process(Resources<SpecificMerchantCriterion> resources) {

        resources.add(
                specificMerchantCriterionLinks.getExportCsvTemplateLink()
        );

        return resources;
    }
}
