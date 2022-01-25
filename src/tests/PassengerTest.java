package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.CruiseShip;
import model.Passenger;

/**
 * Tanner Patterson - tpatterson4
 * CIS175 or CIS152 - SPRING
 * Jan 24, 2022
 */
public class PassengerTest 
{
	private int standardAmt = 1650;
	private int oceanviewAmt = 750;
	private int suiteAmt = 100;
	private int maxCapacity = 2500;
	
	private int interiorCost = 250;
	private int oceanviewCost = 400;
	private int suiteCost = 1000;
	
	private int standardCost = 25;
	private int luxaryCost = 55;
	private int exoticCost = 130;
	
	private int experienceDays = 3;
	private int experienceCost = 200;
	private int voyageLength = 5;
	
	private double markup = 1.25;
	
	private final int STANDARD = 0;
	private final int OCEANVIEW = 1;
	private final int SUITE = 2;
	
	private final int LUXARY = 1;
	private final int EXOTIC = 2;
	
	private int[] roomAmts = {standardAmt, oceanviewAmt, suiteAmt};
	private CruiseShip oceanic = new CruiseShip("Oceanic", maxCapacity, roomAmts); //Name of ship, max passengers, and number of rooms
	
	Passenger p1 = new Passenger(6000); //Budget for passenger
	Passenger p2 = new Passenger(2500);
	Passenger p3 = new Passenger(4300);
	
	@Before
	public void setUp() throws Exception 
	{
		String[] roomTypes = {"Interior", "Oceanview", "Suite"};
		double[] roomCosts = {interiorCost, oceanviewCost, suiteCost};
		
		String[] diningTypes = {"Standard", "Luxary", "Exotic"};
		double[] diningCosts = {standardCost, luxaryCost, exoticCost};
		
		oceanic.setRoomTypes(roomTypes);  //feed data into cruise ship
		oceanic.setRoomCost(roomCosts);
		oceanic.setDiningTypes(diningTypes);
		oceanic.setDiningCost(diningCosts);
		oceanic.setExpDays(experienceDays); //Days passengers can spend doing experiences (at a destination)
		oceanic.setExpCost(experienceCost);
		oceanic.setVoyLength(voyageLength);
		oceanic.setMarkup(markup);
		
		p1.setShip(oceanic);
		p2.setShip(oceanic);
		p3.setShip(oceanic);
	}

	@Test
	public void costTest() //Test to ensure prices are correct
	{
		int[] testAmts = {0, 0, 0};
		oceanic.setRoomAmts(testAmts);
		oceanic.setPassengers(0);
		
		p1.setBudget(100);
		p1.setRoomType(OCEANVIEW); //0 = standard, 1 = oceanview, 2 = suite
		p1.setDiningType(LUXARY); //0 = standard, 1 = luxary, 2 = exotic
		p1.setExpParticipation(true);
		assertFalse(p1.canAffordCruise());
		
		p2.setBudget(2500);
		p2.setRoomType(STANDARD);
		p2.setDiningType(STANDARD);
		p2.setExpParticipation(false);
		assertTrue(p2.canAffordCruise());
	}
	
	@Test
	public void availabilityTest() //Test to ensure cruise cannot be overbooked
	{
		int[] testAmts = {0, 0, 0};
		oceanic.setRoomAmts(testAmts);
		oceanic.setPassengers(0);
		
		p3.setBudget(4000);
		p3.setRoomType(OCEANVIEW);
		p3.setDiningType(LUXARY);
		p3.setExpParticipation(true);
		assertFalse(p3.bookCruise());
		
		int[] testAmts2 = {50, 50, 50};
		oceanic.setRoomAmts(testAmts2);
		oceanic.setPassengers(maxCapacity);
		assertFalse(p3.bookCruise());
		
		oceanic.setPassengers(0);
		assertTrue(p3.bookCruise());
	}

}
