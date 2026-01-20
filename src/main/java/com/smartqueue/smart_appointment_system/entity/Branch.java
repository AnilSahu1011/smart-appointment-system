package com.smartqueue.smart_appointment_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "branches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 250)
    private String address;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(
            mappedBy = "branch",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BusinessService> services;
}
