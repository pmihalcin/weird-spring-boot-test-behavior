package foo.criteria.specificmerchant.web;

import foo.criteria.specificmerchant.SpecificMerchantCriterion;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

/**
 * Helper component to create links to the {@link SpecificMerchantCriterion}.
 * <p>
 * Created by tomas.priecinsky on 22.9.2017.
 */
@ConditionalOnWebApplication
@Component
@RequiredArgsConstructor
public class SpecificMerchantCriterionLinks {

    private static final String FORWARD_SLASH = "/";

    private static final String EDIT_REL = "edit";
    private static final String DELETE_REL = "delete";

    private static final String EXPORT_CSV_TEMPLATE_REL = "exportCsvTemplate";
    static final String EXPORT_CSV_TEMPLATE = FORWARD_SLASH + EXPORT_CSV_TEMPLATE_REL;

    private static final String EXPORT_SPECIFIC_MERCHANT_REL = "export";
    static final String EXPORT_SPECIFIC_MERCHANT = FORWARD_SLASH + EXPORT_SPECIFIC_MERCHANT_REL;

    @NonNull
    private final EntityLinks entityLinks;

    /**
     * Returns the edit {@link Link} for the given {@link SpecificMerchantCriterion}.
     *
     * @param specificMerchantCriterion must not be {@literal null}.
     * @return link
     */
    Link getEditLink(SpecificMerchantCriterion specificMerchantCriterion) {
        return entityLinks.linkForSingleResource(specificMerchantCriterion).withRel(EDIT_REL);
    }

    Link getDeleteLink(SpecificMerchantCriterion specificMerchantCriterion) {
        return entityLinks.linkForSingleResource(specificMerchantCriterion).withRel(DELETE_REL);
    }

    Link getExportSpecificMerchantLink(SpecificMerchantCriterion specificMerchantCriterion) {
        return entityLinks.linkForSingleResource(specificMerchantCriterion).slash(EXPORT_SPECIFIC_MERCHANT).withRel(EXPORT_SPECIFIC_MERCHANT_REL);
    }

    Link getExportCsvTemplateLink() {
        return entityLinks.linkFor(SpecificMerchantCriterion.class).slash(EXPORT_CSV_TEMPLATE).withRel(EXPORT_CSV_TEMPLATE_REL);
    }

}
