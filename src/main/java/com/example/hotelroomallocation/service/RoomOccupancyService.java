package com.example.hotelroomallocation.service;

import com.example.hotelroomallocation.dto.RoomBookingRequest;
import com.example.hotelroomallocation.dto.RoomBookingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomOccupancyService {

  @Value("${premiumThreshold}")
  private BigDecimal premiumThreshold;

  private static final String PREMIUM = "premium";
  private static final String ECONOMY = "economy";

  public RoomBookingResponse calculateOccupancy(RoomBookingRequest request) {

    List<BigDecimal> sortedGuests = sortGuestsDescending(request.potentialGuests());

    Map<String, List<BigDecimal>> partitionedGuests = partitionGuests(sortedGuests);

    List<BigDecimal> premiumGuests = partitionedGuests.get(PREMIUM);
    List<BigDecimal> economyGuests = partitionedGuests.get(ECONOMY);

    List<BigDecimal> premiumOccupancy = allocateRooms(premiumGuests, request.premiumRooms());
    List<BigDecimal> economyOccupancy = allocateRooms(economyGuests, request.economyRooms());

    BigDecimal revenuePremium = calculateRevenue(premiumOccupancy);
    BigDecimal revenueEconomy = calculateRevenue(economyOccupancy);

    return new RoomBookingResponse(
      premiumOccupancy.size(),
      revenuePremium,
      economyOccupancy.size(),
      revenueEconomy
    );
  }

  private List<BigDecimal> sortGuestsDescending(List<BigDecimal> guests) {
    return guests.stream()
      .sorted(Collections.reverseOrder())
      .toList();
  }

  private Map<String, List<BigDecimal>> partitionGuests(List<BigDecimal> guests) {
    return guests.stream()
      .collect(Collectors.groupingBy(this::getGuestCategory));
  }

  private String getGuestCategory(BigDecimal guest) {
    return guest.compareTo(premiumThreshold) >= 0 ? PREMIUM : ECONOMY;
  }

  private List<BigDecimal> allocateRooms(List<BigDecimal> guests, int availableRooms) {
    return guests.stream()
      .limit(availableRooms)
      .collect(Collectors.toList());
  }

  private BigDecimal calculateRevenue(List<BigDecimal> occupancy) {
    return occupancy.stream()
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
