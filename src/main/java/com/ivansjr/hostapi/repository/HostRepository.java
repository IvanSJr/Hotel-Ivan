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
            "FROM Host h JOIN h.checkIn c " +
            "WHERE LOWER(h.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "   OR LOWER(h.document) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "   OR LOWER(h.phone) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<HostResponseDTO> searchHostsByTerm(@Param("searchTerm") String searchTerm);

    @Query("SELECT " +
            "new com.ivansjr.hostapi.service.dto.HostResponseDTO" +
            "(h.name, h.phone, h.document, h.birthdate, c.totalAmountStay, c.totalAmountLastStay, c.totalAmountSpentInTheHotel) " +
            "FROM Host h JOIN h.checkIn c " +
            "WHERE c.isInTheHotel = TRUE " +
            "   AND (LOWER(h.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "       OR LOWER(h.document) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "       OR LOWER(h.phone) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<HostResponseDTO> searchHostsByTermAndCheckInIsInTheHotelTrue(@Param("searchTerm") String searchTerm);

    @Query("SELECT " +
            "new com.ivansjr.hostapi.service.dto.HostResponseDTO" +
            "(h.name, h.phone, h.document, h.birthdate, c.totalAmountStay, c.totalAmountLastStay, c.totalAmountSpentInTheHotel) " +
            "FROM Host h JOIN h.checkIn c " +
            "WHERE c.isInTheHotel = FALSE " +
            "   AND (LOWER(h.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "       OR LOWER(h.document) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "       OR LOWER(h.phone) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<HostResponseDTO> searchHostsByTermAndCheckInIsInTheHotelFalse(@Param("searchTerm") String searchTerm);
}
