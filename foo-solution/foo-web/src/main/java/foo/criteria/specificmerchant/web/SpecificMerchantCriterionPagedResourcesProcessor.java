package foo.criteria.specificmerchant.web;

import foo.criteria.specificmerchant.SpecificMerchantCriterion;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@ConditionalOnWebApplication
@Component
@RequiredArgsConstructor
public class SpecificMerchantCriterionPagedResourcesProcessor implements ResourceProcessor<PagedResources<Resource<SpecificMerchantCriterion>>> {

    @NonNull
    private final SpecificMerchantCriterionLinks specificMerchantCriterionLinks;

    @Override
    public PagedResources<Resource<SpecificMerchantCriterion>> process(PagedResources<Resource<SpecificMerchantCriterion>> resources) {

        resources.add(
                specificMerchantCriterionLinks.getExportCsvTemplateLink()
        );

        return resources;
    }
}
