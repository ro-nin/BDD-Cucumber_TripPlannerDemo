Feature: Find the fastest public transport itinerary to destination
  Scenario: Going from Piazza Cavour to Via Claudio with public transport
    Given the following public transport routes available:
      | Type  | Line Name | Departure name | Destination name |
      | Metro | Linea 2   | Piazza Cavour  | Campi Flegrei    |
      | Bus   | 123       | Cavour         | Tecchio          |
      | Metro | Linea 1   | Museo          | Dante            |
      | Bus   | 456       | Piazza Dante   | Piazzale Tecchio |
    And I am located in "Piazza Cavour"
    And I am willing to use public transport
    And I am not travelling on car
    When I want to go to "Via Claudio"
    Then I should be suggested these itineraries:
      | Linea 2      |
      | 123          |
      | Linea 1, 456 |
