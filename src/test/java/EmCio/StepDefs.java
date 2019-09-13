package EmCio;


import java.util.ArrayList;
import java.util.List;

import EmCio.Model.ParkingSpot;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

enum ItineraryPreference {cheapest, fastest, leastSwitches};

public class StepDefs {
    private List<List<String>> publicTransportsRoutes;
    private List<List<String>> bikeRentalStations;
    private List<List<String>> allKnownParkingSpots;
    private boolean startedToTravelOnCar = false;
    private String departurePoint;
    private String destinationPoint;
    private boolean willingToRentABike;
    private boolean willingToUsePublicTransport;
    private String bikeRentalStationToRentAt = null;
    private String lastLegOfTrasportType;
    ItineraryPreference selectedItineraryPreference = ItineraryPreference.leastSwitches;
    private List<String> obtainedItineraries;
    private List<ParkingSpot> obtainedParkingSpots;

    @Given("the following public transport routes available:")
    public void the_following_public_transport_routes_available(List<List<String>> dataTable) {
        publicTransportsRoutes = dataTable;
    }

    @Given("the following bike rental stations:")
    public void the_following_bike_rental_stations(List<List<String>> dataTable) {
        publicTransportsRoutes = dataTable;
    }

    @Given("there are these parking spots:")
    public void there_are_these_parking_spots(List<List<String>> dataTable) {
        allKnownParkingSpots = dataTable;
    }

    @Given("I am travelling on car")
    public void i_am_travelling_on_car() {
        startedToTravelOnCar = true;
    }

    @Given("I am located in {string}")
    public void i_am_located_in(String string) {
        departurePoint = string;
    }

    @When("I want to go to {string}")
    public void i_want_to_go_to(String string) {
        destinationPoint = string;

        if (startedToTravelOnCar)
            obtainedParkingSpots = stubObtainParkingSpots();

        if (willingToRentABike)
            bikeRentalStationToRentAt = stubGetBikeStationName();

        lastLegOfTrasportType = stubLastLegTypeGenerator();
        obtainedItineraries = stubObtainItineraries();
    }

    @Given("I am willing to rent a bike")
    public void i_am_willing_to_rent_a_bike() {
        willingToRentABike = true;
    }

    @Given("I am not willing to use public transport")
    public void i_am_not_willing_to_use_public_transport() {
        willingToUsePublicTransport = false;
    }

    @Then("I should be suggested to park at {parkingSpot}")
    public void i_should_be_suggested_to_park_at(ParkingSpot ps) {
        Assert.assertEquals(ps, obtainedParkingSpots.get(0));
    }

    @Then("I should be suggested to rent a bike at {string}")
    public void i_should_be_suggested_to_rent_a_bike_at(String string) {
        Assert.assertEquals(string, bikeRentalStationToRentAt);
    }

    private String stubGetBikeStationName() {
        //TODO
        return "Piedigrotta Bikes";
    }

    @Then("I should be suggested to arrive with {string} at destination")
    public void i_should_be_suggested_to_arrive_with_at_destination(String string) {
        Assert.assertEquals(string, lastLegOfTrasportType);
    }

    private String stubLastLegTypeGenerator() {
        //TODO
        if (bikeRentalStationToRentAt != null)
            return "bike";
        else
            return "140";
    }

    @Given("I am not willing to rent a bike")
    public void i_am_not_willing_to_rent_a_bike() {
        willingToRentABike = false;
    }

    @Given("I am willing to use public transport")
    public void i_am_willing_to_use_public_transport() {
        willingToUsePublicTransport = true;
    }

    @Given("I want the cheapest itinerary")
    public void i_want_the_cheapest_itinerary() {
        selectedItineraryPreference = ItineraryPreference.cheapest;
    }

    @Then("My first parking spot returned should be Parcheggio Morelli")
    public void my_first_parking_spot_returned_should_be_Parcheggio_Morelli() {
        Assert.assertEquals(new ParkingSpot("Parcheggio Morelli"), obtainedParkingSpots.get(0));
    }

    @Then("I should be suggested to park in order at:")
    public void i_should_be_suggested_to_park_in_order_at(List<ParkingSpot> dataTable) {
        //TODO
        List<ParkingSpot> expectedParkingSpots = new ArrayList<>();
        for (ParkingSpot parkingS : dataTable
        ) {
            expectedParkingSpots.add(parkingS);
        }
        Assert.assertEquals(expectedParkingSpots, obtainedParkingSpots);
    }

    private List<ParkingSpot> stubObtainParkingSpots() {
        List<ParkingSpot> suggestedParkingSpots = new ArrayList<>();
        ParkingSpot morelli= new ParkingSpot("Parcheggio Morelli");
        ParkingSpot garibaldi= new ParkingSpot("Garage Garibaldi");
        ParkingSpot brin= new ParkingSpot("Parcheggio Brin");
        if (selectedItineraryPreference == ItineraryPreference.cheapest) {
            suggestedParkingSpots.add(morelli);
            suggestedParkingSpots.add(garibaldi);
            suggestedParkingSpots.add(brin);
        } else if (selectedItineraryPreference == ItineraryPreference.fastest || destinationPoint.equals("Piazza Garibaldi")) {
            suggestedParkingSpots.add(garibaldi);
            suggestedParkingSpots.add(brin);
            suggestedParkingSpots.add(morelli);
        } else if (willingToRentABike || willingToUsePublicTransport) {
            ParkingSpot piedigrotta= new ParkingSpot("Piedigrotta Parking");
            suggestedParkingSpots.add(piedigrotta);
        } else if (destinationPoint.equals("Centro Direzionale Isola 1")) {
            ParkingSpot centroDirez= new ParkingSpot("Centro Direzionale Parking");
            suggestedParkingSpots.add(centroDirez);
            suggestedParkingSpots.add(garibaldi);
            suggestedParkingSpots.add(brin);
            suggestedParkingSpots.add(morelli);
        }
        return suggestedParkingSpots;
    }

    @Given("I want the fastest itinerary")
    public void i_want_the_fastest_itinerary() {
        selectedItineraryPreference = ItineraryPreference.fastest;
    }

    @Then("My first parking spot returned should be Garage Garibaldi")
    public void my_first_parking_spot_returned_should_be_Garage_Garibaldi() {
        Assert.assertEquals(new ParkingSpot("Garage Garibaldi"), obtainedParkingSpots.get(0));
    }

    @Given("I am not travelling on car")
    public void i_am_not_travelling_on_car() {
        startedToTravelOnCar = false;
    }

    @Then("I should be suggested these itineraries:")
    public void i_should_be_suggested_these_itineraries(List<String> dataTable) {
        //TODO
        List<String> expectedItineraries = new ArrayList<>();
        for (String parkingName : dataTable) {
            expectedItineraries.add(parkingName);
        }
        Assert.assertEquals(expectedItineraries, obtainedItineraries);
    }

    private List<String> stubObtainItineraries() {
        //TODO
        List<String> itineraries = new ArrayList<>();
        itineraries.add("Linea 2");
        itineraries.add("123");
        itineraries.add("Linea 1, 456");
        return itineraries;
    }


}