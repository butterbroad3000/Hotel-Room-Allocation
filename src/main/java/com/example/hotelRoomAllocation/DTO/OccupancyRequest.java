package com.example.hotelRoomAllocation.DTO;

import java.util.List;

public record OccupancyRequest(
  int premiumRooms,
  int economyRooms,
  List<Double> potentialGuests
) {
}
