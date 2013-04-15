package com.leetchi.api.client.model;

import com.leetchi.api.client.Leetchi;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

public class PaymentCard extends Entity<PaymentCard> {
    public static final String PATH = "cards";

    @JsonProperty("OwnerID")
    private Long ownerId;
    @JsonProperty("CardNumber")
    private String cardNumber;
    @JsonProperty("RedirectURL")
    private String redirectURL;
    @JsonProperty("TemplateURL")
    private String templateURL;
    @JsonProperty("ReturnURL")
    private String returnURL;

    public Long getOwnerId() {
        return ownerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public String getTemplateURL() {
        return templateURL;
    }

    public String getReturnURL() {
        return returnURL;
    }

    @Override
    public String path() {
        return PATH;
    }

    @Override
    public String path(Long id) {
        return path(PATH, id);
    }

    public static PaymentCard newPaymentCards() {
        return new PaymentCard();
    }

    public PaymentCard ownerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public PaymentCard returnUrl(String returnUrl) {
        this.returnURL = returnUrl;
        return this;
    }

    public static PaymentCard fetch(Long id) throws Exception {
        return Leetchi.fetch(path(PaymentCard.PATH, id), PaymentCard.class);
    }

    public static List<PaymentCard> fromUser(Long userId) throws Exception {
        return Leetchi.fetch(path(User.PATH, userId) + "/" + PaymentCard.PATH, new TypeReference<List<PaymentCard>>() { });

    }
}
