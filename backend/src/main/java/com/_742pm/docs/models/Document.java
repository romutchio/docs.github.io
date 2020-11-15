package com._742pm.docs.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private UUID userId;

    private byte[] data;

    public Document() {
    }

    public Document(UUID id, String name, UUID userId, byte[] data) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getId() {
        return id;
    }
}
