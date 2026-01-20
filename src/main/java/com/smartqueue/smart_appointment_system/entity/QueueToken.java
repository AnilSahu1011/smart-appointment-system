package com.smartqueue.smart_appointment_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "queue_tokens",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"business_service_id", "queue_date", "token_number"})
        }
)
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
    private LocalDate queueDate;

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne
    @JoinColumn(
            name = "business_service_id",
            nullable = false
    )
    private BusinessService businessService;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
}

