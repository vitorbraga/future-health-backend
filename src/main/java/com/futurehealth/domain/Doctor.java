package com.futurehealth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctor")
@Data
@EqualsAndHashCode(callSuper = true)
public class Doctor extends User {

    @Column(name = "registry", unique = true)
    private String registry;

    @Column(name = "twitter_url")
    private String twitterUrl;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Education> educations;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "doctor_role",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
