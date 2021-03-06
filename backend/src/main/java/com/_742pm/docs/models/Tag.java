package com._742pm.docs.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tags")
public class Tag
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ApiModelProperty(name = "Название тега")
    private String name;

    @ApiModelProperty(name = "UUID документа")
    private UUID documentId;

    @ApiModelProperty(name = "UUID пользователя")
    private UUID userId;

    public Tag()
    {
    }

    public Tag(UUID id, String name, UUID documentId, UUID userId)
    {
        this.id = id;
        this.name = name;
        this.documentId = documentId;
        this.userId = userId;
    }

    public Tag(String name, UUID documentId, UUID userId)
    {
        this.name = name;
        this.documentId = documentId;
        this.userId = userId;
    }

    public UUID getDocumentId()
    {
        return documentId;
    }

    public UUID getUserId()
    {
        return userId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }
}
