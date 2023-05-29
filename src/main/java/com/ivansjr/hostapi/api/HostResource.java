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

    @GetMapping()
    public ResponseEntity<List<HostResponseDTO>> searchHosts(@RequestParam(required = false, defaultValue = "") String textSearch) {
        List<HostResponseDTO> searchedHosts = hostService.getAll(textSearch);
        return ResponseEntity.ok(searchedHosts);
    }

    @GetMapping("/in-hotel")
    public ResponseEntity<List<HostResponseDTO>> searchHostsInHotel(@RequestParam(required = false, defaultValue = "") String textSearch) {
        List<HostResponseDTO> searchedHostsInHotel = hostService.getAllInHotel(textSearch);
        return ResponseEntity.ok(searchedHostsInHotel);
    }

    @GetMapping("/out-hotel")
    public ResponseEntity<List<HostResponseDTO>> searchHostsOutHotel(@RequestParam(required = false, defaultValue = "") String textSearch) {
        List<HostResponseDTO> searchedHostsOutHotel = hostService.getAllOutHotel(textSearch);
        return ResponseEntity.ok(searchedHostsOutHotel);
    }

    @PatchMapping("/{idHost}/check-in")
    public ResponseEntity<Void> updateCheckInStatus(@PathVariable Long idHost, @RequestBody CheckInUpdateDTO updateDTO) {
        hostService.updateCheckInStatus(idHost, updateDTO);
        return ResponseEntity.noContent().build();
    }
}
