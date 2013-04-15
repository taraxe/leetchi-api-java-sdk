package com.leetchi.api.client.model;

import com.leetchi.api.client.Leetchi;
import org.codehaus.jackson.annotate.JsonProperty;

public class Contribution extends Entity<Contribution> {

    public final static String PATH = "contributions";

    @JsonProperty("UserID")
    private Long userId;
    @JsonProperty("WalletID")
    private Long walletId;
    @JsonProperty("Amount")
    private Long amount;
    @JsonProperty("ClientFeeAmount")
    private Long clientFeeAmount;
    @JsonProperty("LeetchiFeeAmount")
    private Long leetchiFeeAmount;
    @JsonProperty("IsSucceeded")
    private Boolean succeeded;
    @JsonProperty("IsCompleted")
    private Boolean completed;
    @JsonProperty("PaymentURL")
    private String paymentURL;
    @JsonProperty("TemplateURL")
    private String templateURL;
    @JsonProperty("ReturnURL")
    private String returnURL;
    @JsonProperty("RegisterMeanOfPayment")
    private Boolean registerMeanOfPayment;
    @JsonProperty("Error")
    private LeetchiError error;
    @JsonProperty("PaymentCardID")
    private Long paymentCardId;
    @JsonProperty("Culture")
    private String culture;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("PaymentMethodType")
    private String paymentMethodType;
    @JsonProperty("AnswerCode")
    private String answerCode;
    @JsonProperty("AnswerMessage")
    private String answerMessage;

    @Override
    public String path() {
        return PATH;
    }

    @Override
    public String path(Long id) {
        return path(PATH, id);
    }

    public static Contribution newContribution() {
        return new Contribution();
    }

    public Contribution userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Contribution walletId(Long wallerId) {
        this.walletId = wallerId;
        return this;
    }

    public Contribution amount(Long amount) {
        this.amount = amount;
        return this;
    }

    public Contribution returnURL(String returnURL) {
        this.returnURL = returnURL;
        return this;
    }

    public Contribution clientFeeAmount(Long clientFeeAmount) {
        this.clientFeeAmount = clientFeeAmount;
        return this;
    }

    public Contribution templateURL(String templateURL) {
        this.templateURL = templateURL;
        return this;
    }

    public Contribution registerMeanOfPayment(Boolean registerMeanOfPayment) {
        this.registerMeanOfPayment = registerMeanOfPayment;
        return this;
    }

    public Contribution paymentCardId(Long paymentCardId) {
        this.paymentCardId = paymentCardId;
        return this;
    }

    public Contribution paymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
        return this;
    }

    public Contribution type(String type) {
        this.type = type;
        return this;
    }

    public static Contribution fetch(Long contributionId) throws Exception {
       return Leetchi.fetch(path(PATH, contributionId), Contribution.class);
    }

    public Long getUserId() {
        return userId;
    }

    public Long getWalletId() {
        return walletId;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getClientFeeAmount() {
        return clientFeeAmount;
    }

    public Long getLeetchiFeeAmount() {
        return leetchiFeeAmount;
    }

    public Boolean getSucceeded() {
        return succeeded;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public String getPaymentURL() {
        return paymentURL;
    }

    public String getTemplateURL() {
        return templateURL;
    }

    public String getReturnURL() {
        return returnURL;
    }

    public Boolean getRegisterMeanOfPayment() {
        return registerMeanOfPayment;
    }

    public LeetchiError getError() {
        return error;
    }

    public Long getPaymentCardId() {
        return paymentCardId;
    }

    public String getCulture() {
        return culture;
    }

    public String getType() {
        return type;
    }

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public String getAnswerCode() {
        return answerCode;
    }

    public String getAnswerMessage() {
        return answerMessage;
    }
}
