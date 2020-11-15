package com._742pm.docs.models;

import org.springframework.security.oauth2.core.user.OAuth2User;

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

    private String name;

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

    public static User fromPrincipal(OAuth2User principal) {
        var id =  UUID.nameUUIDFromBytes(principal.<String>getAttribute("sub").getBytes());
        var name =  principal.<String>getAttribute("name");
        var picture =  principal.<String>getAttribute("picture");
        return new User(id, name, picture);
    }

}
