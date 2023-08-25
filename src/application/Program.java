package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department department1 = new Department(1, "clothing");
		Seller seller = new Seller(21, "Chuck", "chuch@gmail.com", new Date(), 3000.0, department1);
		
		System.out.println(department1);
		System.out.println(seller);
	}

}
