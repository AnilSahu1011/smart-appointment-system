package com.smartqueue.smart_appointment_system.repository;

import com.smartqueue.smart_appointment_system.entity.QueueToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface QueueTokenRepository extends JpaRepository<QueueToken, Long> {

    @Query("""
    SELECT COALESCE(MAX(q.tokenNumber), 0)
    FROM QueueToken q
    WHERE q.businessService.id = :businessServiceId
      AND q.queueDate = :queueDate
""")
    int findMaxTokenForBusinessServiceAndDate(
            @Param("businessServiceId") Long businessServiceId,
            @Param("queueDate") LocalDate queueDate
    );
}

