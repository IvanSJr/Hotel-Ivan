package com.ivansjr.hostapi.service;

import com.ivansjr.hostapi.domain.CheckIn;
import com.ivansjr.hostapi.domain.Host;
import com.ivansjr.hostapi.repository.CheckInRepository;
import com.ivansjr.hostapi.repository.HostRepository;;
import com.ivansjr.hostapi.service.dto.CheckInUpdateDTO;
import com.ivansjr.hostapi.service.dto.HostCreateDTO;
import com.ivansjr.hostapi.service.dto.HostResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HostService {

    private final HostRepository hostRepository;
    private final CheckInRepository checkInRepository;

    public HostService(HostRepository hostRepository, CheckInRepository checkInRepository) {
        this.hostRepository = hostRepository;
        this.checkInRepository = checkInRepository;
    }

    public Host create(HostCreateDTO hostCreateDTO) {
        return createHost(hostCreateDTO);
    }

    @Transactional(readOnly = true)
    public List<HostResponseDTO> getAll(String search) {
        return hostRepository.searchHostsByTerm(search);
    }

    @Transactional(readOnly = true)
    public List<HostResponseDTO> getAllInHotel(String search) {
        return hostRepository.searchHostsByTermAndCheckInIsInTheHotelTrue(search);
    }

    @Transactional(readOnly = true)
    public List<HostResponseDTO> getAllOutHotel(String search) {
        return hostRepository.searchHostsByTermAndCheckInIsInTheHotelFalse(search);
    }

    private Host createHost(HostCreateDTO hostCreateDTO) {
        Host host = new Host();
        host.setName(hostCreateDTO.getName());
        host.setPhone(hostCreateDTO.getPhone());
        host.setDocument(hostCreateDTO.getDocument());
        host.setBirthdate(hostCreateDTO.getBirthdate());

        CheckIn checkIn = new CheckIn();
        checkIn.setEntryDate(OffsetDateTime.now());
        host.setCheckIn(checkIn);
        checkIn.setHost(host);
        checkIn.setHasCar(hostCreateDTO.getHasCar());
        CheckIn savedCheckIn = checkInRepository.save(checkIn);

        host.setCheckIn(savedCheckIn);

        return hostRepository.save(host);
    }

    public void updateCheckInStatus(Long idHost, CheckInUpdateDTO updateDTO) {
        Optional<Host> optionalHost = hostRepository.findById(idHost);
        if (optionalHost.isPresent()) {
            Host host = optionalHost.get();
            CheckIn checkIn = host.getCheckIn();
            if (checkIn != null) {
                checkIn.setIsInTheHotel(updateDTO.getIsInTheHotel());
                checkInRepository.save(checkIn);
            }
        }
    }
}
