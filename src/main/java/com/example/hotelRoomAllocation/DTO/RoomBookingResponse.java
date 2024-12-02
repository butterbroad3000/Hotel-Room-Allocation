package com.example.hotelRoomAllocation.DTO;

public record RoomBookingResponse(
  int usagePremium,
  double revenuePremium,
  int usageEconomy,
  double revenueEconomy
) {
}
