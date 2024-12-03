package com.example.hotelRoomAllocation.controller;

import com.example.hotelRoomAllocation.dto.RoomBookingRequest;
import com.example.hotelRoomAllocation.service.RoomOccupancyService;
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

    if (request.potentialGuests().stream().anyMatch(guest -> guest < 0)) {
      return ResponseEntity.badRequest().body("All guest amounts must be at least 1");
    }
    return ResponseEntity.ok(roomOccupancyService.calculateOccupancy(request));

  }
}
