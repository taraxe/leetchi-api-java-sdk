package com.leetchi.api.client.model;

import com.leetchi.api.client.Leetchi;
import org.codehaus.jackson.annotate.JsonProperty;

public class Beneficiary extends Entity<Beneficiary> {
    private static final String PATH = "beneficiaries";

    @JsonProperty("UserID")
    private Long userId;
    @JsonProperty("BankAccountOwnerName")
    private String bankAccountOwnerName;
    @JsonProperty("BankAccountOwnerAddress")
    private String bankAccountOwnerAddress;
    @JsonProperty("BankAccountIBAN")
    private String bankAccountIBAN;
    @JsonProperty("BankAccountBIC")
    private String bankAccountBIC;

    Beneficiary() {
    }

    public static Beneficiary newBeneficiary() {
        return new Beneficiary();
    }

    public Beneficiary userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Beneficiary bankAccountOwnerName(String bankAccountOwnerName) {
        this.bankAccountOwnerName = bankAccountOwnerName;
        return this;
    }

    public Beneficiary bankAccountOwnerAddress(String bankAccountOwnerAddress) {
        this.bankAccountOwnerAddress = bankAccountOwnerAddress;
        return this;
    }

    public Beneficiary bankAccountIBAN(String bankAccountIBAN) {
        this.bankAccountIBAN = bankAccountIBAN;
        return this;
    }

    public Beneficiary bankAccountBIC(String bankAccountBIC) {
        this.bankAccountBIC = bankAccountBIC;
        return this;
    }

    public static Beneficiary fetch(Long id) throws Exception {
        return Leetchi.fetch(path(Beneficiary.PATH, id), Beneficiary.class);
    }

    public Long getUserId() {
        return userId;
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

    @Override
    public String path() {
        return PATH;
    }

    @Override
    public String path(Long id) {
        return path(path(), id);
    }
}
