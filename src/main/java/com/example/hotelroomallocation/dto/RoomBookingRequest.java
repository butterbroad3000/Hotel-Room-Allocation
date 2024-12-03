package com.example.hotelroomallocation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record RoomBookingRequest(
  @NotNull
  @Min(0)
  Integer premiumRooms,
  @NotNull
  @Min(0)
  Integer economyRooms,
  @NotEmpty
  List<@Min(0) @NotNull BigDecimal> potentialGuests
) {
}
