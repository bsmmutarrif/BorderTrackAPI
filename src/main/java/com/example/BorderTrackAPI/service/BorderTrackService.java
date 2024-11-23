package com.example.BorderTrackAPI.service;

import com.example.BorderTrackAPI.entity.BorderTrack;
import com.example.BorderTrackAPI.repository.BorderTrackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BorderTrackService {

    private final BorderTrackRepository repository;

    public BorderTrackService(BorderTrackRepository repository) {
        this.repository = repository;
    }

    public BorderTrack logEntry(BorderTrack borderTrack) {
        // to do - check if borderTrack.passID is active !
        borderTrack.setStatus(BorderTrack.Status.ENTERED);
        borderTrack.setEntryDate(LocalDateTime.now());
        return repository.save(borderTrack);
    }

//    private boolean checkPassId(String passId) {
//        // call http://localhost:8080/passpermit/valdate/1
//    }


    @Transactional
    public BorderTrack logExit(Integer passID) {
        BorderTrack borderTrack = repository.findById(passID)
                .orElseThrow(() -> new RuntimeException("Vehicle not found or not entered!"));

        if (!borderTrack.getStatus().equals(BorderTrack.Status.ENTERED)) {
            throw new RuntimeException("Exit is not allowed without an entry!");
        }

        borderTrack.setStatus(BorderTrack.Status.EXITED);
        borderTrack.setExitDate(LocalDateTime.now());
        return repository.save(borderTrack);
    }


//    public BorderTrack logExit(Integer passID) {
//        BorderTrack entry = repository.findByPassIDAndStatus(passID, BorderTrack.Status.ENTERED)
//                .orElseThrow(() -> new IllegalStateException("Vehicle not found or not entered."));
//        entry.setStatus(BorderTrack.Status.EXITED);
//        entry.setExitDate(LocalDateTime.now());
//        return repository.save(entry);
//    }

    public List<BorderTrack> getAllLogs() {
        List<BorderTrack> logs = repository.findAll();
        if (logs.isEmpty()) {
            throw new RuntimeException("No border logs found.");  // Handle empty response
        }
        return logs;
    }


    //

    public List<BorderTrack> getAll() {
        return repository.findAll();
    }

    public Optional<BorderTrack> getById(Integer id) {
        return repository.findById(id);
    }

    //

}