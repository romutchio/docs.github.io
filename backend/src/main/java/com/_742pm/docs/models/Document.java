package com._742pm.docs.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "documents")
public class Document
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ApiModelProperty(name = "Название документа")
    private String name;

    @ApiModelProperty(name = "UUID пользователя")
    private UUID userId;

    @ApiModelProperty(name = "Байтовое представление данных")
    private String data;

    public Document(String name, UUID userId, String data)
    {
        this.name = name;
        this.userId = userId;
        this.data = data;
    }

    public Document()
    {
    }

    public Document(UUID id, String name, UUID userId, String data)
    {
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

}
