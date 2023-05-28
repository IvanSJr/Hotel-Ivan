package com.ivansjr.hostapi.api;

import com.ivansjr.hostapi.domain.Host;
import com.ivansjr.hostapi.service.HostService;
import com.ivansjr.hostapi.service.dto.CheckInUpdateDTO;
import com.ivansjr.hostapi.service.dto.HostCreateDTO;
import com.ivansjr.hostapi.service.dto.HostResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/host")
@RestController
public class HostResource {

    private final HostService hostService;

    public HostResource(HostService hostService) {
        this.hostService = hostService;
    }

    @PostMapping
    public ResponseEntity<Host> createHost(@RequestBody HostCreateDTO hostCreateDTO) {
        Host createdHost = hostService.create(hostCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHost);
    }

    @GetMapping
    public ResponseEntity<List<HostResponseDTO>> findAllHostOrderByEntryDate() {
        return ResponseEntity.ok(hostService.getAll());
    }

    @GetMapping(value = "/in-hotel")
    public ResponseEntity<List<HostResponseDTO>> findAllInHotel() {
        return ResponseEntity.ok(hostService.getAllInHotel());
    }

    @GetMapping(value = "/out-hotel")
    public ResponseEntity<List<HostResponseDTO>> findAllOutHotel() {
        return ResponseEntity.ok(hostService.getAllOutHotel());
    }

    @GetMapping(value = "/document/{document}")
    public ResponseEntity<List<HostResponseDTO>> findAllHostByDocument(@PathVariable String document) {
        return ResponseEntity.ok(hostService.getAllByDocument(document));
    }

    @GetMapping(value = "/phone/{phone}")
    public ResponseEntity<List<HostResponseDTO>> findAllHostByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(hostService.getAllByPhone(phone));
    }

    @PatchMapping("/{idHost}/checkin")
    public ResponseEntity<Void> updateCheckInStatus(@PathVariable Long idHost, @RequestBody CheckInUpdateDTO updateDTO) {
        hostService.updateCheckInStatus(idHost, updateDTO);
        return ResponseEntity.noContent().build();
    }
}
