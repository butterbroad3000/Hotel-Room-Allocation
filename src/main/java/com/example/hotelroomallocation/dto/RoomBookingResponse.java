package com.example.hotelroomallocation.dto;

import java.math.BigDecimal;

public record RoomBookingResponse(
  int usagePremium,
  BigDecimal revenuePremium,
  int usageEconomy,
  BigDecimal revenueEconomy
) {
}
