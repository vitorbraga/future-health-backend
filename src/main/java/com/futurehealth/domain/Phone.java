package com.futurehealth.domain;

import com.futurehealth.enums.PhoneTypeEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "phone")
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Phone {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_type")
    private PhoneTypeEnum phoneType;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
