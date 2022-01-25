package model;

/**
 * Tanner Patterson - tpatterson4
 * CIS175 or CIS152 - SPRING
 * Jan 24, 2022
 */
public class Passenger //Passengers can select a cruise ship and accomodations then get back a total price. This can then be compared to their initial budget to see if they can afford their cruise.
{
	private double budget, roomCost, diningCost, expCost, total;
	private CruiseShip ship;
	private int roomType, diningType;
	private boolean expParticipation;

	/**
	 * default constructor
	 */
	public Passenger() { 
		super();
		budget = 0;
		roomCost = 0;
		diningCost = 0;
		expCost = 0;
		total = 0;
	}

	/**
	 * @param budget
	 */
	public Passenger(double budget) {
		super();
		this.budget = budget;
		roomCost = 0;
		diningCost = 0;
		expCost = 0;
		total = 0;
	}

	/**
	 * @return the budget
	 */
	public double getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(double budget) {
		this.budget = budget;
	}

	/**
	 * @return the ship
	 */
	public CruiseShip getShip() {
		return ship;
	}

	/**
	 * @param ship the ship to set
	 */
	public void setShip(CruiseShip ship) {
		this.ship = ship;
	}

	/**
	 * @return the roomCost
	 */
	public double getRoomCost() {
		return roomCost;
	}

	/**
	 * @return the diningCost
	 */
	public double getDiningCost() {
		return diningCost;
	}

	/**
	 * @return the expCost
	 */
	public double getExpCost() {
		return expCost;
	}

	/**
	 * @return the roomType
	 */
	public int getRoomType() {
		return roomType;
	}

	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(int roomType) {
		this.roomType = roomType;
		roomCost = ship.getRoomCost(roomType);
		updateTotal();
	}

	/**
	 * @return the diningType
	 */
	public int getDiningType() {
		return diningType;
	}

	/**
	 * @param diningType the diningType to set
	 */
	public void setDiningType(int diningType) {
		this.diningType = diningType;
		diningCost = ship.getDiningCost(diningType);
		updateTotal();
	}

	/**
	 * @return the expParticipation
	 */
	public boolean isExpParticipation() {
		return expParticipation;
	}

	/**
	 * @param expParticipation the expParticipation to set
	 */
	public void setExpParticipation(boolean expParticipation) {
		this.expParticipation = expParticipation;
		updateTotal();
	}

	/**
	 * @return the total
	 */
	public double getTotal() 
	{
		updateTotal();
		return total;
	}
	
	public void updateTotal() //method to update the total cost of cruise for the passenger
	{
		if(expParticipation)
		{
			total = (roomCost * ship.getVoyLength()) + (diningCost * (ship.getVoyLength() - ship.getExpDays() * 3)) + (diningCost * (ship.getExpDays() * 2)) + (expCost * ship.getExpDays());
		}
		else
		{
			total = (roomCost * ship.getVoyLength()) + (diningCost * (ship.getVoyLength() * 3));
		}
	}
	
	public boolean canAffordCruise() //method to determine if the passenger can afford the cruise
	{
		updateTotal();
		
		//System.out.println(budget + " " + total);
		if(budget > total)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean bookCruise() //method to confirm options and add passenger to cruise
	{
		return ship.addPassenger(roomType);
	}
}
