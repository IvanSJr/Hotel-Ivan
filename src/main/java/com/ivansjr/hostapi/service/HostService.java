package com.ivansjr.hostapi.service;

import com.ivansjr.hostapi.domain.CheckIn;
import com.ivansjr.hostapi.domain.Host;
import com.ivansjr.hostapi.repository.CheckInRepository;
import com.ivansjr.hostapi.repository.HostRepository;;
import com.ivansjr.hostapi.service.dto.CheckInUpdateDTO;
import com.ivansjr.hostapi.service.dto.HostDTO;
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

    public Host create(HostDTO hostDTO) {
        Host savedHost = createHost(hostDTO);

        return hostRepository.save(savedHost);
    }

    @Transactional(readOnly = true)
    public List<Host> getAll() {
        return hostRepository.findAllByOrderByCheckInEntryDateAsc();
    }

    @Transactional(readOnly = true)
    public List<Host> getAllInHotel() {
        return hostRepository.findByCheckInIsInTheHotelTrue();
    }

    @Transactional(readOnly = true)
    public List<Host> getAllOutHotel() {
        return hostRepository.findByCheckInIsInTheHotelFalse();
    }

    @Transactional(readOnly = true)
    public List<Host> getAllByDocument(String document) {
        return hostRepository.findAllByDocument(document);
    }

    @Transactional(readOnly = true)
    public List<Host> getAllByPhone(String phone) {
        return hostRepository.findAllByPhone(phone);
    }

    private Host createHost(HostDTO hostDTO) {
        Host host = new Host();
        host.setName(hostDTO.getName());
        host.setPhone(hostDTO.getPhone());
        host.setDocument(hostDTO.getDocument());
        host.setBirthdate(hostDTO.getBirthdate());

        CheckIn checkIn = new CheckIn();
        checkIn.setEntryDate(OffsetDateTime.now());
        host.setCheckIn(checkIn);
        checkIn.setHost(host);

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
