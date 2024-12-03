package com.example.hotelRoomAllocation.dto;

public record RoomBookingResponse(
  int usagePremium,
  double revenuePremium,
  int usageEconomy,
  double revenueEconomy
) {
}
