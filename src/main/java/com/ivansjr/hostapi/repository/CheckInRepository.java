package com.ivansjr.hostapi.repository;

import com.ivansjr.hostapi.domain.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
}
