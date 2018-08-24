package Project;

/*
* Names: Rania Farage and Marcus Wallace
* Email: rfarage@gmu.edu and mwalla19@gmu.edu
* Gnumber: G00935520 and G00958383
*/

import java.util.Calendar;

public abstract class Vehicle {
	private String brand;
	private int year;
	private String colour;
	private double cost;
	protected double totalCost;
	public static final double MIN_COST = 0, MIN_YEAR = 1990, MAX_COST = 1000000000;
	
	public Vehicle() {
		
	}
	
	/*Constructor that takes in the brand name of the car, the year, the cost and its colour and creates a Vehicle object
	 */
	public Vehicle(String brand, int year, double cost,  String colour) {
		if(brand.equalsIgnoreCase("") || brand == null) {
			throw new IllegalArgumentException("Error, please enter a brand name");
		}
		if(colour.equalsIgnoreCase("") || colour == null) {
			throw new IllegalArgumentException("Error, please enter a colour");
		}
		if(year<= MIN_YEAR|| year> Calendar.getInstance().get(Calendar.YEAR)) {
			throw new IllegalArgumentException("Error, please enter a valid year");
		}
		if(cost<= MIN_COST) {
			throw new IllegalArgumentException("Error, please enter a valid cost");
		}
		this.brand = brand;
		this.colour = colour;
		this.year = year;
		this.cost = cost;
	}
	
	public String getBrand() {return this.brand;}
	public int getYear() {return this.year;}
	public String getColour() {return this.colour;}
	public double getCost() {return this.cost; }
	public double getTotal() {return this.totalCost;}
	
	public void setBrand(String brand) {
		if(brand.equalsIgnoreCase("") || brand == null) {
			throw new IllegalArgumentException("Error, please enter a brand name");
		}
		this.brand = brand;
	}
	
	public void setColour (String colour) {
		if(colour.equalsIgnoreCase("") || colour == null) {
			throw new IllegalArgumentException("Error, please enter a colour");
		}
		this.colour = colour;
	}
	
	public void setYear (int year) {
		if(year<= MIN_COST || year> Calendar.getInstance().get(Calendar.YEAR)) {
			throw new IllegalArgumentException("Error, please enter a valid date");
		}
		this.year = year;
	}
	
	public void setCost(double cost) {
		if(cost<= MIN_COST || cost>= MAX_COST) {
			throw new IllegalArgumentException("Error, please enter a valid cost");
		}
		this.cost = cost;
	}
	
	//the abstract method
	public abstract void setTotal();
	
	//this method presents a string representation of the car object 
	public String toString() {
		return "Brand: " + this.getBrand() + "  Year: " + this.getYear() + "  Total Cost: " 
				+ String.format("$%.2f",this.getTotal());		
	}
	
}
