package com.ivansjr.hostapi.repository;

import com.ivansjr.hostapi.domain.Host;;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HostRepository extends JpaRepository<Host, Long> {
    List<Host> findAllByOrderByCheckInEntryDateAsc();

    List<Host> findAllByDocument(String document);

    List<Host> findAllByPhone(String phone);

    List<Host> findByCheckInIsInTheHotelTrue();

    List<Host> findByCheckInIsInTheHotelFalse();
}
