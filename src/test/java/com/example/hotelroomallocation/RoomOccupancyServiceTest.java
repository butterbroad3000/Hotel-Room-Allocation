package com.example.hotelroomallocation;


import com.example.hotelroomallocation.dto.RoomBookingRequest;
import com.example.hotelroomallocation.dto.RoomBookingResponse;
import com.example.hotelroomallocation.service.RoomOccupancyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class RoomOccupancyServiceTest {

  @Autowired
  private RoomOccupancyService roomOccupancyService;
  @Mock
  private RoomBookingRequest request;
  private static final List<BigDecimal> DEFAULT_GUESTS = List.of(
    BigDecimal.valueOf(23.0),
    BigDecimal.valueOf(45.0),
    BigDecimal.valueOf(155.0),
    BigDecimal.valueOf(374.0),
    BigDecimal.valueOf(22.0),
    BigDecimal.valueOf(99.99),
    BigDecimal.valueOf(100.0),
    BigDecimal.valueOf(101.0),
    BigDecimal.valueOf(115.0),
    BigDecimal.valueOf(209.0)
  );

  private void setupRequestMock(int premiumRooms, int economyRooms, List<BigDecimal> potentialGuests) {
    when(request.premiumRooms()).thenReturn(premiumRooms);
    when(request.economyRooms()).thenReturn(economyRooms);
    when(request.potentialGuests()).thenReturn(potentialGuests);
  }

  private void assertResponse(RoomBookingResponse response, int usagePremium, BigDecimal revenuePremium, int usageEconomy, BigDecimal revenueEconomy) {
    assertEquals(usagePremium, response.usagePremium());
    assertEquals(revenuePremium, response.revenuePremium());
    assertEquals(usageEconomy, response.usageEconomy());
    assertEquals(revenueEconomy, response.revenueEconomy());
  }

  @Test
  void testCalculateOccupancy_case1() {
    setupRequestMock(3, 3, DEFAULT_GUESTS);
    RoomBookingResponse response = roomOccupancyService.calculateOccupancy(request);
    assertResponse(response, 3, BigDecimal.valueOf(738.0), 3, BigDecimal.valueOf(167.99));
  }

  @Test
  void testCalculateOccupancy_case2() {
    setupRequestMock(7, 5, DEFAULT_GUESTS);
    RoomBookingResponse response = roomOccupancyService.calculateOccupancy(request);
    assertResponse(response, 6, BigDecimal.valueOf(1054.0), 4, BigDecimal.valueOf(189.99));
  }

  @Test
  void testCalculateOccupancy_case3() {
    setupRequestMock(2, 7, DEFAULT_GUESTS);
    RoomBookingResponse response = roomOccupancyService.calculateOccupancy(request);
    assertResponse(response, 2, BigDecimal.valueOf(583.0), 4, BigDecimal.valueOf(189.99));
  }

}
