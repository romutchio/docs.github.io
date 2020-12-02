package com._742pm.docs.controllers;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class DocumentDTO
{
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

    public DocumentDTO(String[] tags, String name, UUID userId, String data)
    {
        this.tags = tags;
        this.name = name;
        this.userId = userId;
        this.data = data;
    }

    public DocumentDTO()
    {
    }

    public DocumentDTO(String[] tags, UUID id, String name, UUID userId, String data)
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

    public String getData()
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

    public void setId(UUID id)
    {
        this.id = id;
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
