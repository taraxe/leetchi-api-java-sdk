package com.leetchi.api.client.model;

import org.codehaus.jackson.annotate.JsonProperty;

public abstract class Entity<T extends Entity> {

    @JsonProperty("ID")
    protected Long id;
    @JsonProperty("Tag")
    private String tag;

    public abstract String path();

    public abstract String path(Long id);

    protected static String path(String path, Long id) {
        return path + "/" + id;
    }

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public T tag(String tag) {
        this.tag = tag;
        return (T) this;
    }

    public String getTag() {
        return tag;
    }

    void setTag(String tag) {
        this.tag = tag;
    }
}
