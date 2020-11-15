package com._742pm.docs.models;

import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ApiModelProperty(name = "Имя пользователя")
    private String name;

    @ApiModelProperty(name = "Ссылка на аватарку")
    private String image_url;

    public User() {
    }

    public User(UUID id, String name, String image_url) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
    }

    public UUID getId() {
        return id;
    }
}
