package com.ysferdgnn.postapp_api.api.database.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import com.ysferdgnn.postapp_api.api.database.models.Users
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "RefreshToken")
data class RefreshToken (
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "usersId", nullable = false)
    val userId: Long? = null,

    @Column(name = "token", nullable = false)
    val token: String? = null,

    @Column(name = "expiryDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val expiryDate: Date? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usersid", nullable = false, insertable = false, updatable = false)
    val users: Users? = null
)