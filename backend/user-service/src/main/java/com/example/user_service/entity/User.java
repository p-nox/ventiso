package com.example.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "users")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( updatable = false)
    private Long id;

    private String name;

    @Column( nullable = false, unique = true)
    private String username;

    @Column( nullable = false)
    private String password;

    @Column( nullable = false, unique = true)
    private String email;

    @Column( name = "created_at",nullable = false, updatable = false)
    private Date createdAt;

    @Column( name = "updated_at")
    private Date updatedAt;


    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

}
