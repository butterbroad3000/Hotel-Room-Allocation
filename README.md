
## Overview
This project provides a service for managing room occupancy, calculating room usage, and revenue based on the potential guests and available rooms.

## Minimum Requirements
- **Java**: 17 or newer

## API Endpoints:
- **POST**:http://localhost:8080/occupancy

* Input:

  ```json
  {
      "premiumRooms": 7,
      "economyRooms": 5,
      "potentialGuests": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
  }
  ```

  * Output:

  ```json
  {
      "usagePremium": 6,
      "revenuePremium": 1054,
      "usageEconomy": 4,
      "revenueEconomy": 189.99
  }
  ```


## Tests

1. ```text
   (input) Free Premium rooms: 3
   (input) Free Economy rooms: 3
   (output) Usage Premium: 3 (EUR 738)
   (output) Usage Economy: 3 (EUR 167.99)
   ```

2. ```text
   (input) Free Premium rooms: 7
   (input) Free Economy rooms: 5
   (output) Usage Premium: 6 (EUR 1054)
   (output) Usage Economy: 4 (EUR 189.99)
   ```

3. ```text
   (input) Free Premium rooms: 2
   (input) Free Economy rooms: 7
   (output) Usage Premium: 2 (EUR 583)
   (output) Usage Economy: 4 (EUR 189.99)
   ```
