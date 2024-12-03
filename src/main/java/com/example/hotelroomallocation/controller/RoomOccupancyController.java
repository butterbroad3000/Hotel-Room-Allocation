package com.example.hotelroomallocation.controller;

import com.example.hotelroomallocation.dto.RoomBookingRequest;
import com.example.hotelroomallocation.service.RoomOccupancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RoomOccupancyController {

  private final RoomOccupancyService roomOccupancyService;

  @PostMapping("/occupancy")
  public ResponseEntity<?> calculateOccupancy(@RequestBody @Valid RoomBookingRequest request) {

    return ResponseEntity.ok(roomOccupancyService.calculateOccupancy(request));

  }
}
