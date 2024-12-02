package com.example.hotelRoomAllocation.Service;

import com.example.hotelRoomAllocation.DTO.RoomBookingRequest;
import com.example.hotelRoomAllocation.DTO.RoomBookingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomOccupancyService {

  @Value("${premiumThreshold}")
  private double premiumThreshold;

  private List<Double> premiumGuests = new ArrayList<>();
  private List<Double> economyGuests = new ArrayList<>();

  public RoomBookingResponse calculateOccupancy(RoomBookingRequest request) {

    List<Double> sortedGuests = new ArrayList<>(request.potentialGuests());
    Collections.sort(sortedGuests, Collections.reverseOrder());

    premiumGuests.clear();
    economyGuests.clear();

    for (double guest : sortedGuests) {
      if (guest >= premiumThreshold) {
        premiumGuests.add(guest);
      } else {
        economyGuests.add(guest);
      }
    }

    List<Double> premiumOccupancy = premiumGuests.subList(0, Math.min(request.premiumRooms(), premiumGuests.size()));
    List<Double> economyOccupancy = economyGuests.subList(0, Math.min(request.economyRooms(), economyGuests.size()));


    double revenuePremium = premiumOccupancy.stream()
      .mapToDouble(Double::doubleValue)
      .sum();

    double revenueEconomy = economyOccupancy.stream()
      .mapToDouble(Double::doubleValue)
      .sum();

    return new RoomBookingResponse(
      premiumOccupancy.size(),
      revenuePremium,
      economyOccupancy.size(),
      revenueEconomy
    );
  }
}

