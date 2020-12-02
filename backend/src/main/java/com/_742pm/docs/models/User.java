package com._742pm.docs.models;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ApiModelProperty(name = "Имя пользователя")
    private String name;
    @ApiModelProperty(name = "Ссылка на аватарку")
    private String imageUrl;

    public User()
    {
    }

    public User(UUID id, String name, String imageUrl)
    {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public static User fromPrincipal(OAuth2User principal)
    {
        var id = UUID.nameUUIDFromBytes(principal.<String>getAttribute("sub").getBytes());
        var name = principal.<String>getAttribute("name");
        var picture = principal.<String>getAttribute("picture");
        return new User(id, name, picture);
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image_url='" + imageUrl + '\'' +
                '}';
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

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

}
