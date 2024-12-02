package com.example.hotelRoomAllocation.DTO;

public record OccupancyResponse(
  int usagePremium,
  double revenuePremium,
  int usageEconomy,
  double revenueEconomy
) {
}
