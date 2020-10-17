package com._742pm.docs.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private UUID documentId;

    private UUID userId;

    public Tag() {
    }

    public Tag(UUID id, String name, UUID documentId, UUID userId) {
        this.id = id;
        this.name = name;
        this.documentId = documentId;
        this.userId = userId;
    }
}
