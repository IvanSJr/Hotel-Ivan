package com.ivansjr.hostapi.repository;

import com.ivansjr.hostapi.domain.Host;;
import com.ivansjr.hostapi.service.dto.HostResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HostRepository extends JpaRepository<Host, Long> {

    @Query("SELECT " +
            "new com.ivansjr.hostapi.service.dto.HostResponseDTO" +
            "(h.name, h.phone, h.document, h.birthdate, c.totalAmountStay, c.totalAmountLastStay, c.totalAmountSpentInTheHotel) " +
            "FROM Host h JOIN h.checkIn c")
    List<HostResponseDTO> findAllHosts();

    @Query("SELECT " +
            "new com.ivansjr.hostapi.service.dto.HostResponseDTO" +
            "(h.name, h.phone, h.document, h.birthdate, c.totalAmountStay, c.totalAmountLastStay, c.totalAmountSpentInTheHotel) " +
            "FROM Host h JOIN h.checkIn c" +
            " WHERE h.document = :document")
    List<HostResponseDTO> findAllByDocument(@Param("document") String document);

    @Query("SELECT " +
            "new com.ivansjr.hostapi.service.dto.HostResponseDTO" +
            "(h.name, h.phone, h.document, h.birthdate, c.totalAmountStay, c.totalAmountLastStay, c.totalAmountSpentInTheHotel) " +
            "FROM Host h JOIN h.checkIn c" +
            " WHERE h.phone = :phone")
    List<HostResponseDTO> findAllByPhone(String phone);

    @Query("SELECT " +
            "new com.ivansjr.hostapi.service.dto.HostResponseDTO" +
            "(h.name, h.phone, h.document, h.birthdate, c.totalAmountStay, c.totalAmountLastStay, c.totalAmountSpentInTheHotel) " +
            "FROM Host h JOIN h.checkIn c" +
            " WHERE c.isInTheHotel = TRUE")
    List<HostResponseDTO> findByCheckInIsInTheHotelTrue();

    @Query("SELECT " +
            "new com.ivansjr.hostapi.service.dto.HostResponseDTO" +
            "(h.name, h.phone, h.document, h.birthdate, c.totalAmountStay, c.totalAmountLastStay, c.totalAmountSpentInTheHotel) " +
            "FROM Host h JOIN h.checkIn c" +
            " WHERE c.isInTheHotel = FALSE")
    List<HostResponseDTO> findByCheckInIsInTheHotelFalse();
}
