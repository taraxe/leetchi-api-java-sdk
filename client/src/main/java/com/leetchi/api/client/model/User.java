package com.leetchi.api.client.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String nationality;
    private String personType;
    private String tag;
    private String ip;
    @JsonProperty("IsStrongAuthenticated")
    private Boolean isStrongAuthenticated;
    private Boolean canRegisterMeanOfPayment;
    private Boolean hasRegisteredMeansOfPayment;
    private Long birthday;
    private Long creationDate;
    private Long updateDate;

    User() {
    }

    public static User newUser() {
        return new User();
    }

    public User firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public User nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public User personType(String personType) {
        this.personType = personType;
        return this;
    }

    public User tag(String tag) {
        this.tag = tag;
        return this;
    }

    @JsonProperty("Tag")
    public String getTag() {
        return tag;
    }

    void setTag(String tag) {
        this.tag = tag;
    }

    @JsonProperty("PersonType")
    public String getPersonType() {
        return personType;
    }

    void setPersonType(String personType) {
        this.personType = personType;
    }

    @JsonProperty("Nationality")
    public String getNationality() {
        return nationality;
    }

    void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("LastName")
    public String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("FirstName")
    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("UpdateDate")
    public Long getUpdateDate() {
        return updateDate;
    }

    void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    @JsonProperty("CreationDate")
    public Long getCreationDate() {
        return creationDate;
    }

    void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("ID")
    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("Birthday")
    public Long getBirthday() {
        return birthday;
    }

    void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    @JsonProperty("HasRegisteredMeansOfPayment")
    public Boolean getHasRegisteredMeansOfPayment() {
        return hasRegisteredMeansOfPayment;
    }

    void setHasRegisteredMeansOfPayment(Boolean hasRegisteredMeansOfPayment) {
        this.hasRegisteredMeansOfPayment = hasRegisteredMeansOfPayment;
    }

    @JsonProperty("CanRegisterMeanOfPayment")
    public Boolean getCanRegisterMeanOfPayment() {
        return canRegisterMeanOfPayment;
    }

    void setCanRegisterMeanOfPayment(Boolean canRegisterMeanOfPayment) {
        this.canRegisterMeanOfPayment = canRegisterMeanOfPayment;
    }

    public Boolean isStrongAuthenticated() {
        return isStrongAuthenticated;
    }

    void setIsStrongAuthenticated(Boolean isStrongAuthenticated) {
        this.isStrongAuthenticated = isStrongAuthenticated;
    }

    @JsonProperty("IP")
    public String getIp() {
        return ip;
    }

    void setIp(String ip) {
        this.ip = ip;
    }
}
