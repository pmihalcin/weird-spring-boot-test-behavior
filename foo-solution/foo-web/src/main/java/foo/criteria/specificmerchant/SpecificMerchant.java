package foo.criteria.specificmerchant;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by patrik.mihalcin on 30.5.2017.
 */
@ToString
@Getter
@Embeddable
public class SpecificMerchant {

    public static final String CSV_HEADER = "merchantId,acquirerId,terminalId";

    private static final String NO_ACQUIRER_ID = null;
    private static final String NO_TERMINAL_ID = null;

    @Column(name = "MERCHANT_ID", length = 128, nullable = false)
    private final String merchantId;
    @Column(name = "ACQUIRER_ID", length = 128)
    private final String acquirerId;
    @Column(name = "TERMINAL_ID", length = 128)
    private final String terminalId;

    protected SpecificMerchant() {
        this.merchantId = null;
        this.acquirerId = null;
        this.terminalId = null;
    }

    private SpecificMerchant(String merchantId, String acquirerId, String terminalId) {
        if (!isMerchantIdValid(merchantId)) {
            throw new IllegalArgumentException(String.format("Invalid merchant ID '%s'!", merchantId));
        }
        this.merchantId = merchantId;

        if (acquirerId != null && !isAcquirerIdValid(acquirerId)) {
            throw new IllegalArgumentException(String.format("Invalid acquirer ID '%s'!", acquirerId));
        }
        this.acquirerId = acquirerId;

        if (terminalId != null && !isTerminalIdValid(terminalId)) {
            throw new IllegalArgumentException(String.format("Invalid terminal ID '%s'!", terminalId));
        }
        this.terminalId = terminalId;
    }

    private SpecificMerchant(String merchantId, String acquirerId) {
        this(merchantId, acquirerId, NO_TERMINAL_ID);
    }

    private SpecificMerchant(String merchantId) {
        this(merchantId, NO_ACQUIRER_ID, NO_TERMINAL_ID);
    }

    private static boolean isMerchantIdValid(String merchantId) {
        return merchantId != null && !merchantId.isEmpty() && merchantId.length() <= 128;
    }

    private static boolean isAcquirerIdValid(String acquirerId) {
        return acquirerId.length() <= 128;
    }

    private static boolean isTerminalIdValid(String terminalId) {
        return terminalId.length() <= 128;
    }

    public static SpecificMerchant of(String merchantId, String acquirerId, String terminalId) {
        return new SpecificMerchant(merchantId, acquirerId, terminalId);
    }

    public static SpecificMerchant of(String merchantId, String acquirerId) {
        return new SpecificMerchant(merchantId, acquirerId);
    }

    public static SpecificMerchant of(String merchantId) {
        return new SpecificMerchant(merchantId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificMerchant that = (SpecificMerchant) o;
        return Objects.equals(merchantId, that.merchantId) &&
                Objects.equals(acquirerId, that.acquirerId) &&
                Objects.equals(terminalId, that.terminalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantId, acquirerId, terminalId);
    }
}
