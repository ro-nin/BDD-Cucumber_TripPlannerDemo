Feature: Find the best multimodal itinerary while travelling on a car
  Background: Available transports
    Given the following public transport routes available:
      | Type  | Line Name | Departure name | Destination name |
      | Bus   | 140       | Piazza Sannazzaro | Piazza Vittoria  |
    And the following bike rental stations:
      | Bike Rental Station Name | Fee per hour |
      | Piedigrotta Bikes        | 2            |
    And there are these parking spots:
      | Parking Spot Name          | Fee per hour |
      | Parcheggio Morelli         | 2            |
      | Piedigrotta Parking        | 5            |

  Scenario: Fastest Multimodal Itinerary with only bike rental permitted
    Given I am travelling on car
    And I am willing to rent a bike
    And I am not willing to use public transport
    And I am located in "Piazzale Tecchio"
    When I want to go to "Piazza Vittoria"
    Then I should be suggested to park at Piedigrotta Parking
    And I should be suggested to rent a bike at "Piedigrotta Bikes"
    And I should be suggested to arrive with "bike" at destination

  Scenario: Fastest Multimodal Itinerary with only public transport permitted
    Given I am travelling on car
    And I am not willing to rent a bike
    And I am willing to use public transport
    And I am located in "Piazzale Tecchio"
    When I want to go to "Piazza Vittoria"
    Then I should be suggested to park at Piedigrotta Parking
    And I should be suggested to arrive with "140" at destination