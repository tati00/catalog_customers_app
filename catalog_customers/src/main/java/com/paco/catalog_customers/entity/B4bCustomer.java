package com.paco.catalog_customers.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "b4b_customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class B4bCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jde_code", nullable = false, unique = true, length = 20)
    private String jdeCode;

    @Column(name = "tax_id", nullable = false, unique = true, length = 13)
    private String taxId;

    @Column(name = "business_name", nullable = false, length = 150)
    private String businessName;

    @Column(name = "commercial_name", length = 150)
    private String commercialName;

    @Column(length = 120)
    private String email;

    @Column(length = 30)
    private String phone;

    @Column(nullable = false, length = 20)
    private String status = "ACTIVE"; // ACTIVE, INACTIVE, BLOCKED

    @Column(name = "credit_limit", precision = 12, scale = 2)
    private BigDecimal creditLimit;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) status = "ACTIVE";
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}