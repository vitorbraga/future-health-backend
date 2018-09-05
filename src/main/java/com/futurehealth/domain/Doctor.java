package com.futurehealth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

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
    private List<Experience> experiences;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Education> educations;
}
