package com.ysferdgnn.postapp_api.api.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "RefreshToken")
public class RefreshToken implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usersId",nullable = false)
    private Long userId;

    @Column(name = "token",nullable = false)
    private String token;

    @Column(name = "expiryDate",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usersid",nullable = false,insertable = false,updatable = false)
    private Users users;
}
