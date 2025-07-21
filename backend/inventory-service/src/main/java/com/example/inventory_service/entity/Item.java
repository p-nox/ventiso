package com.example.inventory_service.entity;

import com.example.inventory_service.enums.ItemCondition;
import com.example.inventory_service.enums.ItemStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String title;

    private String description;

    @Column( nullable = false)
    private Float price;

    @Enumerated(EnumType.STRING)
    @Column(name = "`condition`", nullable = false)
    private ItemCondition condition; // New-Used-Like new....

    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private ItemStatus status;  //Active-sold-hidden...


    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
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

/*
@Enumerated(EnumType.STRING)
    private ItemCondition condition; // Enum για ασφάλεια τύπου

    @Enumerated(EnumType.STRING)
    private ItemStatus status; // Enum για valid καταστάσεις (ACTIVE, SOLD κλπ)

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

 */