package com._742pm.docs.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
    private Byte[] data;

    public Document()
    {
    }

    public Document(UUID id, String name, UUID userId, Byte[] data)
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

    public Byte[] getData()
    {
        return data;
    }

    public void setData(Byte[] data)
    {
        this.data = data;
    }

    public UUID getId()
    {
        return id;
    }
}
