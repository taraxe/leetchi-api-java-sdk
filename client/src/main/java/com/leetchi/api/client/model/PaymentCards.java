package com.leetchi.api.client.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class PaymentCards extends Entity<PaymentCards> {
    private static final String PATH = "cards";

    @JsonProperty("ownerID")
    private Long OwnerID;
    @JsonProperty("cardNumber")
    private String CardNumber;
    @JsonProperty("redirectURL")
    private String RedirectURL;
    @JsonProperty("templateURL")
    private String TemplateURL;
    @JsonProperty("returnURL")
    private String ReturnURL;

    public Long getOwnerID() {
        return OwnerID;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public String getRedirectURL() {
        return RedirectURL;
    }

    public String getTemplateURL() {
        return TemplateURL;
    }

    public String getReturnURL() {
        return ReturnURL;
    }

    @Override
    public String path() {
        return PATH;
    }

    @Override
    public String path(Long id) {
        return path(PATH, id);
    }
}
