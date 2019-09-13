Feature: Find the best parking spot near destination

  Background:
    Given there are these parking spots:
      | Parking Spot Name          | Fee per hour |
      | Centro Direzionale Parking | 6            |
      | Garage Garibaldi           | 4            |
      | Parcheggio Brin            | 10           |
      | Parcheggio Morelli         | 2            |
    And I am located in 'Via Morghen'
    And I am travelling on car
    And I am not willing to use public transport
    And I am not willing to rent a bike

  Scenario: Going with car from Via Morghen to Centro Direzionale
    When I want to go to "Centro Direzionale Isola 1"
    Then I should be suggested to park in order at:
      | Centro Direzionale Parking |
      | Garage Garibaldi           |
      | Parcheggio Brin            |
      | Parcheggio Morelli         |

  Scenario Outline: Best parking from Via Morghen to Centro Direzionale for cost and  travel time
    Given I want the <mode> itinerary
    When I want to go to "Piazza Garibaldi"
    Then My first parking spot returned should be <parkingSpotName>
    Examples:
      | mode     | parkingSpotName    |
      | cheapest | Parcheggio Morelli |
      | fastest  | Garage Garibaldi   |
