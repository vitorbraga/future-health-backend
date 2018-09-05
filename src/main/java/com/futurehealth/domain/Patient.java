package com.futurehealth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
@Data
@EqualsAndHashCode(callSuper = true)
public class Patient extends User {
}
