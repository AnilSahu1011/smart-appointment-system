package com.smartqueue.smart_appointment_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(
            name = "business_service_id",
            nullable = false
    )
    private BusinessService businessService;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(nullable = false, length = 30)
    private String currentStatus;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private QueueToken queueToken;

    @OneToMany(
            mappedBy = "appointment",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<StatusHistory> statusHistoryList;
}

