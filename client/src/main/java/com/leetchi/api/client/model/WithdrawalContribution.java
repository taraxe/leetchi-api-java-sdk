package com.leetchi.api.client.model;

import com.leetchi.api.client.Leetchi;
import org.codehaus.jackson.annotate.JsonProperty;

public class WithdrawalContribution extends Entity<WithdrawalContribution> {

    public final static String PATH = "contributions-by-withdrawal";

    @JsonProperty("UserID")
    private Long userId;
    @JsonProperty("WalletID")
    private Long walletId;
    @JsonProperty("Amount")
    private Long amount;
    @JsonProperty("AmountDeclared")
    private Long amountDeclared;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("GeneratedReference")
    private String generatedReference;
    @JsonProperty("Commentary")
    private String commentary;
    @JsonProperty("BankAccountOwner")
    private String bankAccountOwner;
    @JsonProperty("BankAccountIBAN")
    private String bankAccountIBAN;
    @JsonProperty("BankAccountBIC")
    private String bankAccountBIC;


    @Override
    public String path() {
        return PATH;
    }

    @Override
    public String path(Long id) {
        return path(PATH, id);
    }

    public WithdrawalContribution userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public WithdrawalContribution walletId(Long wallerId) {
        this.walletId = wallerId;
        return this;
    }

    public WithdrawalContribution amountDeclared(Long amountDeclared) {
        this.amountDeclared = amountDeclared;
        return this;
    }

    public static WithdrawalContribution fetch(Long withdrawalContributionId) throws Exception {
        return Leetchi.fetch(path(PATH, withdrawalContributionId), WithdrawalContribution.class);
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


    public static WithdrawalContribution newWithdrawalContribution() {
        return new WithdrawalContribution();
    }

    public Long getAmountDeclared() {
        return amountDeclared;
    }

    public String getStatus() {
        return status;
    }

    public String getGeneratedReference() {
        return generatedReference;
    }

    public String getCommentary() {
        return commentary;
    }

    public String getBankAccountOwner() {
        return bankAccountOwner;
    }

    public String getBankAccountIBAN() {
        return bankAccountIBAN;
    }

    public String getBankAccountBIC() {
        return bankAccountBIC;
    }
}
