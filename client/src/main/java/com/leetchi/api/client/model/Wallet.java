package com.leetchi.api.client.model;

import com.leetchi.api.client.Leetchi;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.type.TypeReference;

import java.util.Arrays;
import java.util.List;

public class Wallet extends Entity<Wallet>{
    private static final String PATH = "wallets";

    @JsonProperty("Owners")
    private List<Long> ownersIds;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("RaisingGoalAmount")
    private Long raisingGoalAmount;
    @JsonProperty("CollectedAmount")
    private Long collectedAmount;
    @JsonProperty("SpentAmount")
    private Long spentAmount;
    @JsonProperty("RemainingAmount")
    private Long remainingAmount;
    @JsonProperty("IsClosed")
    private Boolean closed;
    @JsonProperty("ContributionLimitDate")
    private Long contributionLimitDate;
    @JsonProperty("ExpirationDate")
    private Long  expirationDate;

    public static List<Wallet> fromUser(Long userId) throws Exception {
        return Leetchi.fetch(path(User.PATH, userId) + "/" + Wallet.PATH, new TypeReference<List<Wallet>>() { });
    }

    public static List<User> users(Long walletId) throws Exception {
        return Leetchi.fetch(path(Wallet.PATH, walletId) + "/" + User.PATH, new TypeReference<List<User>>() { });
    }

    public static Wallet fetch(Long walletId) throws Exception {
        return Leetchi.fetch(path(Wallet.PATH, walletId), Wallet.class);
    }

    public List<User> users() throws Exception {
        return users(id);
    }

    public static Wallet newWallet() {
        return new Wallet();
    }

    public Wallet name(String name) {
        this.name = name;
        return this;
    }

    public Wallet ownersIds(Long... ids) {
        this.ownersIds = Arrays.asList(ids);
        return this;
    }

    public Wallet raisingGoalAmount(Long raisingGoalAmount) {
        this.raisingGoalAmount = raisingGoalAmount;
        return this;
    }

    public List<Long> getOwnersIds() {
        return ownersIds;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getRaisingGoalAmount() {
        return raisingGoalAmount;
    }

    public Long getCollectedAmount() {
        return collectedAmount;
    }

    public Long getSpentAmount() {
        return spentAmount;
    }

    public Long getRemainingAmount() {
        return remainingAmount;
    }

    public Long getContributionLimitDate() {
        return contributionLimitDate;
    }

    public Boolean getClosed() {
        return closed;
    }

    @Override
    public String path() {
        return PATH;
    }

    @Override
    public String path(Long id) {
        return path(PATH, id);
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public Wallet description(String description) {
        this.description = description;
        return this;
    }

    public Wallet contributionLimitDate(Long contributionLimitDate) {
        this.contributionLimitDate = contributionLimitDate;
        return this;
    }
}
