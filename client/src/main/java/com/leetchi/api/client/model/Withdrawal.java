package com.leetchi.api.client.model;

import com.leetchi.api.client.Leetchi;
import org.codehaus.jackson.annotate.JsonProperty;

public class Withdrawal extends Entity<Withdrawal> {

    public final static String PATH = "withdrawals";

    @JsonProperty("UserID")
    private Long userId;
    @JsonProperty("WalletID")
    private Long walletId;
    @JsonProperty("Amount")
    private Long amount;
    @JsonProperty("AmountWithoutFees")
    private Long amountWithouFees;
    @JsonProperty("ClientFeeAmount")
    private Long clientFeeAmount;
    @JsonProperty("LeetchiFeeAmount")
    private Long leetchiFeeAmount;
    @JsonProperty("IsSucceeded")
    private Boolean succeeded;
    @JsonProperty("IsCompleted")
    private Boolean completed;
    @JsonProperty("BeneficiaryID")
    private Long beneficiaryId;
    @JsonProperty("Error")
    private LeetchiError error;
    @JsonProperty("BankAccountOwnerName")
    private String bankAccountOwnerName;
    @JsonProperty("BankAccountOwnerAddress")
    private String bankAccountOwnerAddress;
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

    public Withdrawal userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Withdrawal walletId(Long wallerId) {
        this.walletId = wallerId;
        return this;
    }

    public Withdrawal amount(Long amount) {
        this.amount = amount;
        return this;
    }

    public Withdrawal clientFeeAmount(Long clientFeeAmount) {
        this.clientFeeAmount = clientFeeAmount;
        return this;
    }

    public Withdrawal beneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
        return this;
    }

    public static Withdrawal fetch(Long immediateContributionId) throws Exception {
        return Leetchi.fetch(path(PATH, immediateContributionId), Withdrawal.class);
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

    public Long getAmountWithouFees() {
        return amountWithouFees;
    }

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public static Withdrawal newWithdrawal() {
        return new Withdrawal();
    }

    public String getBankAccountOwnerName() {
        return bankAccountOwnerName;
    }

    public String getBankAccountOwnerAddress() {
        return bankAccountOwnerAddress;
    }

    public String getBankAccountIBAN() {
        return bankAccountIBAN;
    }

    public String getBankAccountBIC() {
        return bankAccountBIC;
    }
}
