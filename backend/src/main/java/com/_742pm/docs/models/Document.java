package com._742pm.docs.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.ArrayUtils;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "documents")
public class Document
{
    @ApiModelProperty(name = "Теги документа")
    private String[] tags;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ApiModelProperty(name = "Название документа")
    private String name;

    @ApiModelProperty(name = "UUID пользователя")
    private UUID userId;

    @ApiModelProperty(name = "Байтовое представление данных")
    private String data;

    public Document(String[] tags, String name, UUID userId, String data)
    {
        this.tags = tags;
        this.name = name;
        this.userId = userId;
        this.data = data;
    }

    public Document()
    {
    }

    public Document(String[] tags, UUID id, String name, UUID userId, String  data)
    {
        this.tags = tags;
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.data = data;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public UUID getUserId()
    {
        return userId;
    }

    public void setUserId(UUID userId)
    {
        this.userId = userId;
    }

    public String  getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public UUID getId()
    {
        return id;
    }

    public String[] getTags()
    {
        return tags;
    }

    public void setTags(String[] tags)
    {
        this.tags = tags;
    }
}
