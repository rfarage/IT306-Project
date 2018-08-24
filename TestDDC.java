package Project;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

import org.junit.Test;

/*
* Names: Rania Farage and Marcus Wallace
* Email: rfarage@gmu.edu and mwalla19@gmu.edu
* Gnumber: G00935520 and G00958383
*/

public class TestDDC {
	
	@Test
	//note! If the test fails, please check line 45. 
	public void test() {
		//below tests the setTax, setModel and setFees methods while checking the Car class constructor
		Car one=  new Car("chrysler",2015,15000, "gray");
		one.setTax(3);
		one.setModel("200");
		one.setFees(200);
		
		
		assertEquals("Brand: chrysler  Year: 2015  Total Cost: $15650.00 Model: 200  Car ID: 1000", one.toString());
		
		Car two = new Car("BMW", 2017, 26000, "red");
		two.setTax(10);
		two.setModel("M3");
		two.setFees(0);
		
		
		assertEquals("Brand: BMW  Year: 2017  Total Cost: $28600.00 Model: M3  Car ID: 1001", two.toString());
		
		//below we are testing the fillMap method in the implementation class
		Implementation test = new Implementation();
		Map<String, Car> a = new HashMap<String, Car>();
		try {
			Scanner scan = new Scanner ( new FileInputStream(new File("carList.txt")));
			test.fillMap(a, scan);
			//note: below will only pass if there is only 1 car entry in the car list text file. 
			//If there is 2 or more car entries, please change the number '1' below to the number of entries in the file.
			assertEquals(1,a.size());
		}
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Error, the file cannot be found" );
		}
		
	}

}
