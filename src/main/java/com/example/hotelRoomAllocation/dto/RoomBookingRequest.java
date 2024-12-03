package com.example.hotelRoomAllocation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RoomBookingRequest(
  @NotNull
  @Min(0)
  Integer premiumRooms,
  @NotNull
  @Min(0)
  Integer economyRooms,
  @NotNull
  @Size(min = 0)
  List<Double> potentialGuests
) {
}
