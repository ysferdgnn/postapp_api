package com.ysferdgnn.postapp_api.api.database.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
@Data
@ApiModel(value = "Comment Model")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "Comment model autogenerated Id value")
    private Long id;

    @Column(name = "usersId",nullable = false)
    @ApiModelProperty(value = "User Id for describe who commented post")
    private Long usersId;

    @Column(name = "postId",nullable = false)
    @ApiModelProperty(value = "Post Id for describe which post commented")
    private Long postId;

    @Column(name = "text",length = 500)
    @ApiModelProperty(value = "define post content")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usersId",insertable = false,updatable = false,nullable = false)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId",insertable = false,updatable = false,nullable = false)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
}
