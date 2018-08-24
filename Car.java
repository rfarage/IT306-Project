package Project;

/*
* Names: Rania Farage and Marcus Wallace
* Email: rfarage@gmu.edu and mwalla19@gmu.edu
* Gnumber: G00935520 and G00958383
*/


public class Car extends Vehicle{
	private double tax;
	private String model;
	private static int allCars;
	private static int carID = 1000;
	private String iD;
	private double fees;
	public static double TAX_PERCENTAGE = 100;
	
	public Car() {
		super();
		allCars++;
		carID++;
	}
	
	/*Constructor that takes in the brand name of the car, the year, the cost and its colour and creates a Car object, also
	 * calling the super constructor. 
	 */
	public Car(String brand, int year, double cost, String colour) {
		super(brand, year, cost, colour);
		allCars++;
		this.iD = carID + "";
		carID++;
	}
	
	public double getTax() {return this.tax;}
	public String getModel() {return this.model;}
	public double getFees() {return this.fees;}
	public static int getAllCars() {return allCars;}
	public String getID() {return this.iD;}
	
	public void setID(String ID) {
		this.iD = ID;
	}
	//this method takes in the tax percentage that the user entered and converts it to a decimal 
	public void setTax(double tax) {
		if (tax<MIN_COST || tax > TAX_PERCENTAGE) {
			throw new IllegalArgumentException("Error, please enter a tax percentage between 0 and 100");
		}
		this.tax = tax + TAX_PERCENTAGE;
		this.tax = this.tax/TAX_PERCENTAGE;
	}
	
	public void setModel(String model) {
		if(model.equalsIgnoreCase("") || model == null) {
			throw new IllegalArgumentException("Error, please enter a model name/number");
		}
		this.model = model;
	}
	
	public void setFees(double fees) {
		if(fees<MIN_COST) {
			throw new IllegalArgumentException("Error, please enter a fee charge greater than or equal to 0");
		}
		this.fees = fees;
		setTotal();
	}
	
	//this is the overridden setTotal method that determines the total cost of the car.
	//it multiplies the cost with the tax, and then adds any extra fees to the cost 
	public void setTotal() {
		if(this.tax >MIN_COST) {
			super.totalCost = super.getCost()*this.tax;
		}
		else {
			super.totalCost = super.getCost();
		}
		super.totalCost += this.fees; 
	}
	
	
	//this method presents a string representation of the car object 
	public String toString() {
		return super.toString()+" Model: " + this.getModel()+ "  Car ID: " + this.getID();
		
	}
	
}
