package com.example.BorderTrackAPI.repository;

import com.example.BorderTrackAPI.entity.BorderTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorderTrackRepository extends JpaRepository<BorderTrack, Integer> {
    Optional<BorderTrack> findByPassIDAndStatus(Integer passID, BorderTrack.Status status);
}