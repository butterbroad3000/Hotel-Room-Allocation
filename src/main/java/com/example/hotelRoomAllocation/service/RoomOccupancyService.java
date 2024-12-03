package com.example.hotelRoomAllocation.service;

import com.example.hotelRoomAllocation.dto.RoomBookingRequest;
import com.example.hotelRoomAllocation.dto.RoomBookingResponse;
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

  public RoomBookingResponse calculateOccupancy(RoomBookingRequest request) {

    List<Double> sortedGuests = sortGuestsDescending(request.potentialGuests());
    List<Double> premiumGuests = new ArrayList<>();
    List<Double> economyGuests = new ArrayList<>();

    partitionGuests(sortedGuests, premiumGuests, economyGuests);

    List<Double> premiumOccupancy = allocateRooms(premiumGuests, request.premiumRooms());
    List<Double> economyOccupancy = allocateRooms(economyGuests, request.economyRooms());

    double revenuePremium = calculateRevenue(premiumOccupancy);
    double revenueEconomy = calculateRevenue(economyOccupancy);

    return new RoomBookingResponse(
      premiumOccupancy.size(),
      revenuePremium,
      economyOccupancy.size(),
      revenueEconomy
    );
  }

  private List<Double> sortGuestsDescending(List<Double> guests) {
    List<Double> sortedGuests = new ArrayList<>(guests);
    Collections.sort(sortedGuests, Collections.reverseOrder());
    return sortedGuests;
  }

  private void partitionGuests(List<Double> guests, List<Double> premiumGuests, List<Double> economyGuests) {
    for (double guest : guests) {
      if (guest >= premiumThreshold) {
        premiumGuests.add(guest);
      } else {
        economyGuests.add(guest);
      }
    }
  }

  private List<Double> allocateRooms(List<Double> guests, int availableRooms) {
    return guests.subList(0, Math.min(availableRooms, guests.size()));
  }

  private double calculateRevenue(List<Double> occupancy) {
    return occupancy.stream()
      .mapToDouble(Double::doubleValue)
      .sum();
  }
}

