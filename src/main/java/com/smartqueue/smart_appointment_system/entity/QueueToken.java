package com.smartqueue.smart_appointment_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "queue_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer tokenNumber;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
}
