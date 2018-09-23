package com.futurehealth.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
@Data
@EqualsAndHashCode(exclude = {"createdAt", "updatedAt"})
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "created_at")
    @Setter(AccessLevel.NONE)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    @Setter(AccessLevel.NONE)
    private Instant updatedAt = Instant.now();

    @Version
    private Integer version;

    @PreUpdate
    public void setLastUpdate() {
        this.updatedAt = Instant.now();
    }

}
