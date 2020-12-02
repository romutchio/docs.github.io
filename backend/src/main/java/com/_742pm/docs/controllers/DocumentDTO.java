package com._742pm.docs.controllers;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.UUID;

@Entity
public class DocumentDTO
{
    @Override
    public String toString()
    {
        return "DocumentDTO{" +
                "tags=" + Arrays.toString(tags) +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentDTO that = (DocumentDTO) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : 0;
    }

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
