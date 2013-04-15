package com.leetchi.api.client.model;

import com.leetchi.api.client.Leetchi;
import org.codehaus.jackson.annotate.JsonProperty;

public class ImmediateContribution extends Entity<ImmediateContribution> {

    public final static String PATH = "immediate-contributions";

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
    @JsonProperty("Error")
    private LeetchiError error;
    @JsonProperty("PaymentCardID")
    private Long paymentCardId;
    @JsonProperty("Culture")
    private String culture;
    @JsonProperty("Type")
    private String type;
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

    public ImmediateContribution userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public ImmediateContribution walletId(Long wallerId) {
        this.walletId = wallerId;
        return this;
    }

    public ImmediateContribution amount(Long amount) {
        this.amount = amount;
        return this;
    }

    public ImmediateContribution clientFeeAmount(Long clientFeeAmount) {
        this.clientFeeAmount = clientFeeAmount;
        return this;
    }


    public ImmediateContribution paymentCardId(Long paymentCardId) {
        this.paymentCardId = paymentCardId;
        return this;
    }


    public ImmediateContribution type(String type) {
        this.type = type;
        return this;
    }

    public static ImmediateContribution fetch(Long immediateContributionId) throws Exception {
        return Leetchi.fetch(path(PATH, immediateContributionId), ImmediateContribution.class);
    }

    public String getAnswerCode() {
        return answerCode;
    }

    public String getType() {
        return type;
    }

    public String getCulture() {
        return culture;
    }

    public Long getPaymentCardId() {
        return paymentCardId;
    }

    public LeetchiError getError() {
        return error;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Boolean getSucceeded() {
        return succeeded;
    }

    public Long getLeetchiFeeAmount() {
        return leetchiFeeAmount;
    }

    public Long getClientFeeAmount() {
        return clientFeeAmount;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getWalletId() {
        return walletId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAnswerMessage() {
        return answerMessage;
    }

    public static ImmediateContribution newImmediateContribution() {
        return new ImmediateContribution();
    }
}
