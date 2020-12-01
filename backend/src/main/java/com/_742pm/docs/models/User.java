package com._742pm.docs.models;

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
                ", image_url='" + imageUrl + '\'' +
                '}';
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ApiModelProperty(name = "Имя пользователя")
    private String name;

    @ApiModelProperty(name = "Ссылка на аватарку")
    private String imageUrl;

    public User() {
    }

    public User(UUID id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
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
