package model;

import java.util.Arrays;

/**
 * Tanner Patterson - tpatterson4
 * CIS175 or CIS152 - SPRING
 * Jan 24, 2022
 */
public class CruiseShip //Cruise ship class, passengers can book trips on cruise ship.
{
	private String name;
	private int expDays, voyLength, passengers, maxPassengers;
	private double markup, expCost;
	private String[] roomTypes, diningTypes;
	private double[] roomCost, diningCost;
	private int[] roomAmts;
	private int[] maxRoomAmts;
	
	/**
	 * 
	 */
	public CruiseShip() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param maxPassengers
	 */
	public CruiseShip(String name, int maxPassengers, int[] roomAmts) {
		super();
		this.name = name;
		this.maxPassengers = maxPassengers;
		this.roomAmts = roomAmts;
		maxRoomAmts = roomAmts;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the expDays
	 */
	public int getExpDays() {
		return expDays;
	}

	/**
	 * @param expDays the expDays to set
	 */
	public void setExpDays(int expDays) {
		this.expDays = expDays;
	}

	/**
	 * @return the voyLength
	 */
	public int getVoyLength() {
		return voyLength;
	}

	/**
	 * @param voyLength the voyLength to set
	 */
	public void setVoyLength(int voyLength) {
		this.voyLength = voyLength;
	}

	/**
	 * @return the passengers
	 */
	public int getPassengers() {
		return passengers;
	}

	/**
	 * @param passengers the passengers to set
	 */
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	/**
	 * @return the maxPassengers
	 */
	public int getMaxPassengers() {
		return maxPassengers;
	}

	/**
	 * @param maxPassengers the maxPassengers to set
	 */
	public void setMaxPassengers(int maxPassengers) {
		this.maxPassengers = maxPassengers;
	}

	/**
	 * @return the markup
	 */
	public double getMarkup() {
		return markup;
	}

	/**
	 * @param markup the markup to set
	 */
	public void setMarkup(double markup) {
		this.markup = markup;
	}

	/**
	 * @return the roomTypes
	 */
	public String[] getRoomTypes() {
		return roomTypes;
	}

	/**
	 * @param roomTypes the roomTypes to set
	 */
	public void setRoomTypes(String[] roomTypes) {
		this.roomTypes = roomTypes;
	}

	/**
	 * @return the diningTypes
	 */
	public String[] getDiningTypes() {
		return diningTypes;
	}

	/**
	 * @param diningTypes the diningTypes to set
	 */
	public void setDiningTypes(String[] diningTypes) {
		this.diningTypes = diningTypes;
	}

	/**
	 * @return the roomCost
	 */
	public double getRoomCost(int type) {
		return roomCost[type];
	}

	/**
	 * @param roomCost the roomCost to set
	 */
	public void setRoomCost(double[] roomCost) {
		this.roomCost = roomCost;
	}

	/**
	 * @return the diningCost
	 */
	public double getDiningCost(int type) {
		return diningCost[type];
	}

	/**
	 * @param diningCost the diningCost to set
	 */
	public void setDiningCost(double[] diningCost) {
		this.diningCost = diningCost;
	}

	/**
	 * @return the expCost
	 */
	public double getExpCost() {
		return expCost;
	}

	/**
	 * @param expCost the expCost to set
	 */
	public void setExpCost(double expCost) {
		this.expCost = expCost;
	}

	/**
	 * @return the roomAmts
	 */
	public int[] getRoomAmts() {
		return roomAmts;
	}

	/**
	 * @param roomAmts the roomAmts to set
	 */
	public void setRoomAmts(int[] roomAmts) {
		this.roomAmts = roomAmts;
	}

	/**
	 * @return the maxRoomAmts
	 */
	public int[] getMaxRoomAmts() {
		return maxRoomAmts;
	}

	/**
	 * @param maxRoomAmts the maxRoomAmts to set
	 */
	public void setMaxRoomAmts(int[] maxRoomAmts) {
		this.maxRoomAmts = maxRoomAmts;
	}

	public String getStatus() //Returns status of ship
	{
		if(passengers < (maxPassengers * .75))
		{
			return "Rooms available!";
		}
		else if((passengers > (maxPassengers * .75)) && (passengers < maxPassengers))
		{
			return "Some rooms left, almost booked!";
		}
		else
		{
			return "Booked!";
		}
	}
	
	public boolean addPassenger(int roomType) //Adds passenger to ship
	{
		if((passengers < maxPassengers) && (roomAmts[roomType] > 0))
		{
			passengers++;
			roomAmts[roomType]--;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public double detMarkup(int roomType) //If ship is greater than 95% capacity or room type almost sold out, mark up price for that person.
	{
		if((passengers > (maxPassengers * .95)) || (roomAmts[roomType] < maxRoomAmts[roomType] * .05))
		{
			return markup;
		}
		else
		{
			return 1;
		}
	}

	@Override
	public String toString() {
		return "CruiseShip [name=" + name + ", expDays=" + expDays + ", voyLength=" + voyLength + ", passengers="
				+ passengers + ", maxPassengers=" + maxPassengers + ", markup=" + markup + ", expCost=" + expCost
				+ ", roomTypes=" + Arrays.toString(roomTypes) + ", diningTypes=" + Arrays.toString(diningTypes)
				+ ", roomCost=" + Arrays.toString(roomCost) + ", diningCost=" + Arrays.toString(diningCost)
				+ ", roomAmts=" + Arrays.toString(roomAmts) + ", maxRoomAmts=" + Arrays.toString(maxRoomAmts) + "]";
	}
	
	
}
