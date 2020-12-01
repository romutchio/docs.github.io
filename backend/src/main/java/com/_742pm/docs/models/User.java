package com._742pm.docs.models;

import com._742pm.docs.controllers.DocumentsController;
import com._742pm.docs.controllers.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.user.OAuth2User;

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

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }

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

    public static User fromPrincipal(OAuth2User principal) {
        var id =  UUID.nameUUIDFromBytes(principal.<String>getAttribute("sub").getBytes());
        var name =  principal.<String>getAttribute("name");
        var picture =  principal.<String>getAttribute("picture");
        return new User(id, name, picture);
    }

}
