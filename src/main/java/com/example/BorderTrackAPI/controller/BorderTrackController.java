package com.example.BorderTrackAPI.controller;

import com.example.BorderTrackAPI.entity.BorderTrack;
import com.example.BorderTrackAPI.message.ResponseMessage;
import com.example.BorderTrackAPI.service.BorderTrackService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bordertrack/")
public class BorderTrackController {

    private final BorderTrackService service;

    public BorderTrackController(BorderTrackService service) {
        this.service = service;
    }

    @GetMapping("/api/bordertrack/all")
    public ResponseEntity<?> getAllLogs() {
        try {
            List<BorderTrack> logs = service.getAllLogs();

            // If logs are found, return them with a success message
            return ResponseEntity.ok(new ResponseMessage("Border logs retrieved successfully.", logs));

        } catch (RuntimeException e) {
            // If no logs are found or another error occurs
            return ResponseEntity.ok(new ResponseMessage(e.getMessage(), null));
        }
    }




    @PostMapping("/entry")
    public ResponseEntity<?> logEntry(@Valid @RequestBody BorderTrack borderTrack) {
        try {
            BorderTrack loggedEntry = service.logEntry(borderTrack);
            return ResponseEntity.ok(new ResponseMessage("Entry logged successfully.", loggedEntry));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage(), null));
        }
    }


    @PutMapping("/exit/{passID}")
    public ResponseEntity<?> logExit(@PathVariable Integer passID) {
        try {
            BorderTrack loggedExit = service.logExit(passID);
            return ResponseEntity.ok(new ResponseMessage("Exit logged successfully.", loggedExit));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage(), null));
        }
    }


    //

    //



     //

    @GetMapping("/{id}")
    public ResponseEntity<BorderTrack> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //


}