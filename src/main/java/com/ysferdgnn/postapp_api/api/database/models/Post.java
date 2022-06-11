package com.ysferdgnn.postapp_api.api.database.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Post")
@Getter
@Setter
@ApiModel(value = "Post Model")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "Post model autogenerated Id value")
    private Long id;

    @Column(name = "title")
    @ApiModelProperty(value = "Post model title value for describe post content")
    private String title;

    @Column(name = "text",length = 500)
    @ApiModelProperty(value = "Post model content text")
    private String text;

    @Column(name = "usersid",nullable = false)
    @ApiModelProperty(value = "describe who made post ")
    private Long usersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersid",insertable = false,nullable = false,updatable = false)
    @JsonIgnore
    private Users users;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "postid",insertable = false,nullable = false,updatable = false)
    @JsonIgnore
    private List<Likes> likes;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "postid",insertable = false,nullable = false,updatable = false)
    @JsonIgnore
    private List<Comment> comments;

}
