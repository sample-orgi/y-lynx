package com.trss.bi.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "templateFavorites")
public class TemplateFavorites implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    @NotNull
    @Field("user_id")
    private Long userId;

    @NotNull
    @Field("template_ids")
    private List<String> templateIds = new ArrayList<>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public TemplateFavorites id(ObjectId id) {
        setId(id);
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public TemplateFavorites userId(Long userId) {
        setUserId(userId);
        return this;
    }

    public List<String> getTemplateIds() {
        return templateIds;
    }

    public void setTemplateIds(List<String> templateIds) {
        this.templateIds = templateIds;
    }

    public TemplateFavorites templateIds(List<String> templateIds) {
        setTemplateIds(templateIds);
        return this;
    }
}
