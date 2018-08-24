package Project;

/*
* Names: Rania Farage and Marcus Wallace
* Email: rfarage@gmu.edu and mwalla19@gmu.edu
* Gnumber: G00935520 and G00958383
* gitHub Link: https://github.com/rfarage/IT306/wiki
*/


import java.util.*;
import java.io.*;
import java.util.Map.Entry;

import javax.swing.*;

public class Implementation {

	public static void main(String[] args) {
		final int ONE = 1, TWO = 2, THREE = 3, FOUR = 4, FIVE = 5;
		//boolean used in the do while loop to ensure the user chooses between 1 and 3, when choosing what sorting method 
		//to implement
		boolean found = false;
		HashMap<String, Car> map = new HashMap<String, Car>();
		
		//if the file is not empty, then we will add its contents to the existing hashmap using the fillMap method
		try {
			Scanner scan = new Scanner ( new FileInputStream(new File("carList.txt")));
			if(scan.hasNextLine()) {
				fillMap(map, scan);
			}
		}
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Error, the file cannot be found" );
		
		}
		
		//the int below represents the user choice as to what they would like to do 
		int option = getOption();
		//A hashmap is used to store all of the information about all cars, where each car ID is the key and the Car object is the
		//value
		
		
		while(option != FIVE) {
			if(option==ONE) {
				addCar(map);
			}
			else if(option==TWO) {
				viewCars();
			}
			else if(option==THREE) {
				searchCars(map);
			}
			else if (option==FOUR) {
				//the user has to choose which type of sorting method they would like to access and that is what the 
				//statements below represent
				do {
				try {
					int sortOption = Integer.parseInt(JOptionPane.showInputDialog("Would like to sort based on:" + "\n1.Price" + "\n2.Year" + "\n3.Brand Name"));
					if(sortOption == ONE) {
						sortCarsCost(map);
						found = true;
					}
					else if(sortOption == TWO) {
						sortCarsYear(map);
						found = true;
					}
					else if(sortOption == THREE) {
						sortCarsName(map);
						found = true;
					}
					if(!found) {
						JOptionPane.showMessageDialog(null, "Error, please choose a number between 1 and 3");
					}
				}
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Error, please enter a number");
				}
				}while(!found);
			}
			option = getOption();
		}
		//if the user chooses to exit the program, a thank you message will be displayed
		JOptionPane.showMessageDialog(null, "Thank you for using the program");
	}

//method that displays the menu with the choices of options that the user has 
//@return menu - the menu choice that the user chose 
	public static int getOption() {
		int menu = 0;
		final int ONE = 1, FIVE = 5, INVALID = 0;
	//do while loop that prompts the user input (checking to see they entered one of the menu choices) 
	do {
		try {
			menu = Integer.parseInt(JOptionPane.showInputDialog("What would you like to do?   " + "\n" + "1: Add a car" + "\n" 
		+ "2: View all cars" + "\n" + "3: Search for cars" + "\n" + "4: Sort cars" + "\n" + "5: Exit system"));	
		}
		catch(NumberFormatException e) {
			menu = INVALID;
		}
		if(menu<ONE || menu >FIVE) {
			JOptionPane.showMessageDialog(null, "Error, please choose a number between 1 and 5");
		}
		
	 }while(menu<ONE || menu > FIVE);
	 return menu;
	}
	
	//this method below fills all the information in the file, into the hashmap
	//it does this by creating a new car object for each entry in the file and enters it into the hashmap
	//@param map - the hashmap to fill
	//@param scan - the scanner object created in the main method
	public static void fillMap(Map<String, Car> map, Scanner scan) {
		Scanner scan2 = null;
		String line = null;
		while(scan.hasNextLine()) {
			line = scan.nextLine();
			//if statement used to ensure that if the file has a blank line, it wont be entered into the hashmap, thus preventing 
			//an error from occurring. 
			if(line != null && !line.equalsIgnoreCase("")) {
				scan2 = new Scanner(line);
				scan2.useDelimiter(":");
				String name = scan2.next();
				name = scan2.next();
				int end = name.indexOf("Year");
				name = name.substring(0, end).trim();
				String temp = scan2.next();
				int endYear = temp.indexOf("Total Cost");
				temp = temp.substring(0, endYear);
				int year = Integer.parseInt(temp.trim());
				String tempC = scan2.next();
				int endCost = tempC.indexOf("Model");
				tempC = tempC.substring(2, endCost);
				double cost = Double.parseDouble(tempC.trim());
				String tempM = scan2.next();
				int endM = tempM.indexOf("Car ID");
				tempM = tempM.substring(1, endM).trim();
				String ID = scan2.next().trim();
				//below we are creating a Car object and filling its variables with the appropriate information from above
				try {
					Car now = new Car();
					now.setBrand(name);
					now.setYear(year);
					now.setCost(cost);
					now.setTotal();
					now.setModel(tempM);
					now.setID(ID);
					//below we are adding the entry into the hashmap
					if(!map.containsKey(ID)) {
						map.put(ID, now);
					}
				}
				catch(IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
		scan.close();
		
	}
//method that prompts user for the car information and adds it to the hashmap
//@param hashmap that holds cars 

	public static void addCar(Map<String, Car> map) {
		Vehicle one;
		//the booleans below are used to validation purposes 
		boolean pass = false, passM= false, passT = false, passF = false;
		do {
			try {
				//we create a new car based on user input
				one = new Car(JOptionPane.showInputDialog("Enter the brand of the car: "),Integer.parseInt(
			       JOptionPane.showInputDialog("Enter the year of the car")), Double.parseDouble(
					       JOptionPane.showInputDialog("Enter the cost of the car: ")), JOptionPane.showInputDialog("Enter the color of the vehicle: "));
				pass = true;
				
					
						if(one instanceof Car) {
							Car a = (Car)one;
							do {
								try {
									a.setModel(JOptionPane.showInputDialog("Enter the model of the car: "));
									passM = true;
								}
								catch(NumberFormatException e) {
									JOptionPane.showMessageDialog(null, "Error, please enter a number");
								}
								catch(IllegalArgumentException e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}	
								catch(NullPointerException e) {
									JOptionPane.showMessageDialog(null, "Error, please enter a value and do not click cancel");
								}
							}while(!passM);
							do {
								try {
									a.setTax(Double.parseDouble(JOptionPane.showInputDialog("Enter percent of tax e.g. for 5% you can enter just 5: ")));
									passT = true;
								}
								catch(NumberFormatException e) {
									JOptionPane.showMessageDialog(null, "Error, please enter a number");
								}
								catch(IllegalArgumentException e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
								catch(NullPointerException e) {
									JOptionPane.showMessageDialog(null, "Error, please enter a value and do not click cancel");
								}
							}while(!passT);
							do {
								try {
									a.setFees(Double.parseDouble(JOptionPane.showInputDialog("Enter fees for car. If there are none, please enter 0")));
									passF = true;
								}
								catch(NumberFormatException e) {
									JOptionPane.showMessageDialog(null, "Error, please enter a number");
								}
								catch(IllegalArgumentException e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}	
								catch(NullPointerException e) {
									JOptionPane.showMessageDialog(null, "Error, please enter a value and do not click cancel");
								}
							}while(!passF);
							//below we are adding the car object to the hashmap 
							map.put(a.getID(), a);
						}
						//Below we are storing the car info to a file
						String path = "carList.txt";
						try {	
							FileWriter fw = new FileWriter(path, true);
				    		BufferedWriter bw = new BufferedWriter(fw);
				    		PrintWriter out = new PrintWriter(bw);
				    		out.print("\n"+one.toString());
				    		out.close();
						//Throws an error message if the text file is not found 
						}catch(FileNotFoundException e){
							JOptionPane.showMessageDialog(null,  "Car file could not be found");
						}catch(IOException e){
							JOptionPane.showMessageDialog(null, e);
						}	
			
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error, please enter a number");
			}
			catch(IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}			
			catch(NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Error, one of the values you entered is empty. Please enter a value for each variable and do not click cancel");
			}
		}while(!pass);
		
	}
	
	
//method that displays all the cars using the information from the text file
	
	public static void viewCars() {
		String output = "***All Cars***";
		String path = "carList.txt";
		//Below we are using a Scanner to read all info in the file and display to user
		try {
			//Reads the text file that has the list of car info
			Scanner scan = new Scanner ( new FileInputStream(new File(path)));
			//loop that goes through the each line of the text file and stores the car info into a string to print
			 while(scan.hasNextLine()) {
				output  += "\n" + scan.nextLine();
			}
			scan.close();
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null,  e);
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, e);
		}	
		
		//if the output equals ***All Cars*** that means the file is empty and nothing has been read, therefore the 
		//string message will be printed that no cars have been added
		//otherwise the output will be printed 
		if (output.equalsIgnoreCase("***All Cars***")) {
			JOptionPane.showMessageDialog(null, "No cars have been entered yet");
		}
		else {
			JOptionPane.showMessageDialog(null, output);
		}
	}

	//method that sorts the cars based on price
	  //@param hashmap to hold the data
	public static void sortCarsCost(HashMap<String, Car> map) {
		//if the map is empty, a message should be displayed
		if (map.size() == 0 ) {
			JOptionPane.showMessageDialog(null, "No cars have been entered yet");
		}
		else {
			String output = "**Sorted Cars By Price**" + "\n";
		
			//We are copying all the content in the hashmap to a new hashmap
			//This is done so we can manipulate/sort the new hashmap, without distorting the original one
			Map<String, Car> oldMap = new HashMap<String, Car>();
			oldMap.putAll(map);
			
			
			double min = 1000000000;
			//A carMin is created to save the information of the car with the lowest cost
			Car carMin = map.get("1000");
			Iterator it = oldMap.entrySet().iterator();
			while(oldMap.size() != 0) {
				while(it.hasNext()) {
					Entry<String, Car> e = (Entry<String, Car>)it.next();
					//determining the minimum cost and setting it to the min
					if(e.getValue().getTotal() < min) {
						min = e.getValue().getTotal();
						carMin = e.getValue();
						
					}
				}
				//The car with the lowest cost is saved into a string to print
				output += carMin.toString() + "\n";
				//the car with the lowest cost is removed from the map so that the next lowest cost can be found
				oldMap.remove(carMin.getID());
				//the new entry set is entered into the iterator object 'it
				it = oldMap.entrySet().iterator();
				//the minimum value is reset
				min = 1000000000;
			}
		
		
			JOptionPane.showMessageDialog(null,  output);
		}
	}
	
	//method that sorts the cars based on year
	  //@param hashmap to hold the data
	public static void sortCarsYear(HashMap<String, Car> map) {
		//if the map is empty, a message should be displayed
		if (map.size() == 0 ) {
			JOptionPane.showMessageDialog(null, "No cars have been entered yet");
		}
		else {
			String output = "**Sorted Cars By Year**" + "\n";
		
			//We are copying all the content in the hashmap to a new hashmap
			//This is done so we can manipulate/sort the new hashmap, without distorting the original one
			Map<String, Car> oldMap = new HashMap<String, Car>();
			oldMap.putAll(map);
			
			
			int min = 2018;
			//A carMin is created to save the information of the car with the lowest year
			Car carMin = map.get("1000");
			Iterator it = oldMap.entrySet().iterator();
			while(oldMap.size() != 0) {
				while(it.hasNext()) {
					Entry<String, Car> e = (Entry<String, Car>)it.next();
					//determining the minimum year and setting it to the min
					if(e.getValue().getYear() <= min) {
						min = e.getValue().getYear();
						carMin = e.getValue();
						
					}
				}
				//The car with the lowest year is saved into a string to print
				output += carMin.toString() + "\n";
				//the car with the lowest year is removed from the map so that the next lowest year can be found
				oldMap.remove(carMin.getID());
				//the new entry set is entered into the iterator object 'it'
				it = oldMap.entrySet().iterator();
				//the minimum value is reset
				min = 2018;
			}
		
		
			JOptionPane.showMessageDialog(null,  output);
		}
	}
	
	//method that sorts the cars based on brand name
	  //@param hashmap to hold the data
	public static void sortCarsName(HashMap<String, Car> map) {
		//if the map is empty, a message should be displayed
		if (map.size() == 0 ) {
			JOptionPane.showMessageDialog(null, "No cars have been entered yet");
		}
		else {
			String output = "**Sorted Cars By Brand Name**" + "\n";
		
			//We are copying all the content in the hashmap to a new hashmap
			//This is done so we can manipulate/sort the new hashmap, without distorting the original one
			Map<String, Car> oldMap = new HashMap<String, Car>();
			oldMap.putAll(map);
			
			
			String min = "z";
			//A carMin is created to save the information of the car with the lowest name
			Car carMin = map.get("1000");
			Iterator it = oldMap.entrySet().iterator();
			while(oldMap.size() != 0) {
				while(it.hasNext()) {
					Entry<String, Car> e = (Entry<String, Car>)it.next();
					//determining the minimum name and setting it to the min
					if(e.getValue().getBrand().compareTo(min) <= 0) {
						min = e.getValue().getBrand();
						carMin = e.getValue();
						
					}
				}
				//The car with the lowest name is saved into a string to print
				output += carMin.toString() + "\n";
				//the car with the lowest name is removed from the map so that the next lowest name can be found
				oldMap.remove(carMin.getID());
				//the new entry set is entered into the iterator object 'it
				it = oldMap.entrySet().iterator();
				//the minimum value is reset
				min = "z";
				
			}
		
		
			JOptionPane.showMessageDialog(null,  output);
		}
	}
	//method that searches for cars that match the id that the customer enters and then displays the car that matches the ID
    //@param map - hashmap 
	
	public static void searchCars(HashMap<String, Car> map) {
		//if the map is empty, a message should be displayed
	  if (map.size() == 0 ) {
		  JOptionPane.showMessageDialog(null, "No cars have been entered yet");
	  }
	  else {
		  //a boolean is created for validation purposes
		  boolean found  = false;
		  
		  
			String path = "carList.txt";
			//we will create a menu to display a list of all cars so the user knows which ID belongs to which car
			String[] menu = new String[map.size()];
			//the counter is used for the menu
			int counter = 0;

			
			//Below we are using a Scanner to read all info in the file and display to user
			try {
				//Reads the text file that has the list of car info
				Scanner scan = new Scanner ( new FileInputStream(new File(path)));
				//loop that goes through the each line of the text file and stores the car info into a string array to print
				 while(scan.hasNextLine() && counter<map.size()) {
					menu[counter] = scan.nextLine();
					counter++;
				}
				scan.close();
				
			}catch(FileNotFoundException e){
				JOptionPane.showMessageDialog(null,  e);
			}catch(IOException e){
				JOptionPane.showMessageDialog(null, e);
			}	
			
			
			

			
		  //below the info of a car is searched for based on the car ID entered
		  do {
			  try {
				  
				  
				  //the car id is determined from the users choice based off of a drop down menu
				  String id = (String)JOptionPane.showInputDialog(null,  "Please choose one of the following: ", "Car Inventory", JOptionPane.QUESTION_MESSAGE, null,
				            menu, menu[0]);
				  //in the 2 statements below, we are determining the beginning index of the car id number
				  int beg = id.indexOf("D:");
				  beg += 2;
				  //the id is substringed so that only the car ID number is stored
				  id = id.substring(beg).trim();
				  //if the id exists in the hashmap, the car information will be printed. Otherwise, and error message will display
				  if(map.containsKey(id)) {
					  //the output variable is created to display the car information to the user
					  String output = "The information of the car you chose is displayed below: \n";
					  //the info variable is created to manipulate the output for the user
					  String info = map.get(id).toString();
					  int begs = info.indexOf("d:");
					  begs += 2;
					  int end =info.indexOf("Year:"); 
					  int end2 = info.indexOf("Total Cost:");
					  int end3 = info.indexOf("Model");
					  int end4 = info.indexOf("Car ID:");
					  output +=  "\n" + info.substring(end4) + "\n" + "Brand Name: " + info.substring(begs, end) + "\n" + info.substring(end3,end4)
							+  "\n"+  info.substring(end, end2) + "\n" +info.substring(end2, end3);
					  JOptionPane.showMessageDialog(null,  output);
					  found = true;
				  }
				  else {
					  JOptionPane.showMessageDialog(null, "Error, the id you entered cannot be found");
				  }
			  }
			  catch(NumberFormatException e) {
				  JOptionPane.showMessageDialog(null,  "Error, please enter a number");
			  }
		  }while(!found);
	  }
	}
}
