package com.futurehealth.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "experience")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "location")
    private String location;

    @Column(name = "created_at")
    @Setter(AccessLevel.NONE)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    @Setter(AccessLevel.NONE)
    private Instant updatedAt = Instant.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    @PreUpdate
    public void setLastUpdate() {  this.updatedAt = Instant.now(); }
}
