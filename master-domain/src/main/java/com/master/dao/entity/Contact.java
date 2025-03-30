package com.master.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "contact")
@Data
public class Contact extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contactName;

    @Column(nullable = false)
    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "ss_master_id", nullable = false)
    private SafetyServiceProvider safetyService;
}
