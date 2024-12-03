package com.example.hotelRoomAllocation;


import com.example.hotelRoomAllocation.dto.RoomBookingRequest;
import com.example.hotelRoomAllocation.dto.RoomBookingResponse;
import com.example.hotelRoomAllocation.service.RoomOccupancyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class RoomOccupancyServiceTest {

  @Autowired
  private RoomOccupancyService roomOccupancyService;
  @Mock
  private RoomBookingRequest request;

  @Test
  void testCalculateOccupancy_case1() {
    when(request.premiumRooms()).thenReturn(3);
    when(request.economyRooms()).thenReturn(3);
    when(request.potentialGuests()).thenReturn(List.of(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

    RoomBookingResponse response = roomOccupancyService.calculateOccupancy(request);

    assertEquals(3, response.usagePremium());
    assertEquals(738, response.revenuePremium());
    assertEquals(3, response.usageEconomy());
    assertEquals(167.99, response.revenueEconomy());
  }

  @Test
  void testCalculateOccupancy_case2() {
    when(request.premiumRooms()).thenReturn(7);
    when(request.economyRooms()).thenReturn(5);
    when(request.potentialGuests()).thenReturn(List.of(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

    RoomBookingResponse response = roomOccupancyService.calculateOccupancy(request);

    assertEquals(6, response.usagePremium());
    assertEquals(1054, response.revenuePremium());
    assertEquals(4, response.usageEconomy());
    assertEquals(189.99, response.revenueEconomy());
  }

  @Test
  void testCalculateOccupancy_case3() {
    when(request.premiumRooms()).thenReturn(2);
    when(request.economyRooms()).thenReturn(7);
    when(request.potentialGuests()).thenReturn(List.of(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

    RoomBookingResponse response = roomOccupancyService.calculateOccupancy(request);

    assertEquals(2, response.usagePremium());
    assertEquals(583, response.revenuePremium());
    assertEquals(4, response.usageEconomy());
    assertEquals(189.99, response.revenueEconomy());
  }

}
