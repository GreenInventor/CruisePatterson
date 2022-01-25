package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.CruiseShip;

/**
 * Tanner Patterson - tpatterson4
 * CIS175 or CIS152 - SPRING
 * Jan 24, 2022
 */
public class CruiseShipTest 
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
	
	private final int STANDARD = 0;
	private final int OCEANVIEW = 1;
	private final int SUITE = 2;
	
	private double markup = 1.25;
	
	private int[] roomAmts = {standardAmt, oceanviewAmt, suiteAmt};
	private CruiseShip oceanic = new CruiseShip("Oceanic", maxCapacity, roomAmts); //Name of ship, max passengers, and number of rooms

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
	}

	@Test
	public void testStatus() 
	{
		oceanic.setPassengers(0);
		assertEquals("Rooms available!", oceanic.getStatus());
		
		oceanic.setPassengers((int)((maxCapacity * .95)));
		assertEquals("Some rooms left, almost booked!", oceanic.getStatus());
		
		oceanic.setPassengers(maxCapacity);
		assertEquals("Booked!", oceanic.getStatus());
	}

	@Test
	public void addPassengerTest()
	{
		oceanic.setPassengers(0);
		
		int[] testAmts = {standardAmt, oceanviewAmt, suiteAmt}; //Amount of rooms remaining
		int[] testAmts2 = {standardAmt, oceanviewAmt, suiteAmt}; //2nd value had to be created which doesn't equal oceanics room amounts so the value doesn't change all by itself
		
		oceanic.setRoomAmts(testAmts); //Set rooms remaining on ship oceanic to same as testAmt
		assertArrayEquals(testAmts2, oceanic.getRoomAmts()); //confirm values are the same
		
		assertTrue(oceanic.addPassenger(OCEANVIEW)); //0 = standard, 1 = oceanview, 2 = suite
		testAmts2[OCEANVIEW]--;
		assertArrayEquals(testAmts2, oceanic.getRoomAmts());
		
		assertTrue(oceanic.addPassenger(STANDARD));
		assertTrue(oceanic.addPassenger(OCEANVIEW));
		assertTrue(oceanic.addPassenger(OCEANVIEW));
		assertTrue(oceanic.addPassenger(SUITE));
		
		testAmts2[STANDARD]--;
		testAmts2[OCEANVIEW]--;
		testAmts2[OCEANVIEW]--;
		testAmts2[SUITE]--;
		
		assertArrayEquals(testAmts2, oceanic.getRoomAmts());
		assertEquals(5, oceanic.getPassengers());
	}
	
	@Test
	public void detMarkupTest() //Test to make sure markup price works when ship is almost to capacity or rooms almost sold out.
	{
		oceanic.setPassengers(0);
		oceanic.setMarkup(markup);
		
		int[] testAmts = {0, 0, 0};
		oceanic.setRoomAmts(testAmts);
		
		assertEquals(markup, oceanic.detMarkup(0), 0);
		
		testAmts[OCEANVIEW] = (int) (oceanviewAmt);
		oceanic.setRoomAmts(testAmts);
		
		assertEquals(1, oceanic.detMarkup(1), 0);
		
		oceanic.setPassengers((int) (maxCapacity * .96));
		assertEquals(markup, oceanic.detMarkup(1), 0);
	}
	
}
