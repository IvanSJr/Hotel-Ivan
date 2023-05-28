package com.ivansjr.hostapi.api;

import com.ivansjr.hostapi.domain.Host;
import com.ivansjr.hostapi.service.HostService;
import com.ivansjr.hostapi.service.dto.CheckInUpdateDTO;
import com.ivansjr.hostapi.service.dto.HostDTO;
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
    public ResponseEntity<Host> createHost(@RequestBody HostDTO hostDTO) {
        Host createdHost = hostService.create(hostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHost);
    }

    @GetMapping
    public ResponseEntity<List<Host>> findAllHostOrderByEntryDate() {
        return ResponseEntity.ok(hostService.getAll());
    }

    @GetMapping(value = "/in-hotel")
    public ResponseEntity<List<Host>> findAllInHotel() {
        return ResponseEntity.ok(hostService.getAllInHotel());
    }

    @GetMapping(value = "/out-hotel")
    public ResponseEntity<List<Host>> findAllOutHotel() {
        return ResponseEntity.ok(hostService.getAllOutHotel());
    }

    @GetMapping(value = "/{document}")
    public ResponseEntity<List<Host>> findAllHostByDocument(@PathVariable String document) {
        return ResponseEntity.ok(hostService.getAllByDocument(document));
    }

    @GetMapping(value = "/{phone}")
    public ResponseEntity<List<Host>> findAllHostByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(hostService.getAllByPhone(phone));
    }

    @PatchMapping("/{idHost}/checkin")
    public ResponseEntity<Void> updateCheckInStatus(@PathVariable Long idHost, @RequestBody CheckInUpdateDTO updateDTO) {
        hostService.updateCheckInStatus(idHost, updateDTO);
        return ResponseEntity.noContent().build();
    }
}
