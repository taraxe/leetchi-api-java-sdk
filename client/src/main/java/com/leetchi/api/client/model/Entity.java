package com.leetchi.api.client.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public abstract class Entity<T extends Entity> {

    @JsonProperty("ID")
    protected Long id;
    @JsonProperty("Tag")
    private String tag;
    @JsonProperty("CreationDate")
    private Long creationDate;
    @JsonProperty("UpdateDate")
    private Long updateDate;

    public abstract String path();

    public abstract String path(Long id);

    public static String path(String path, Long id) {
        return path + "/" + id;
    }

    public Long getId() {
        return id;
    }

    public T tag(String tag) {
        this.tag = tag;
        return (T) this;
    }

    public String getTag() {
        return tag;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public Long getCreationDate() {
        return creationDate;
    }

}
