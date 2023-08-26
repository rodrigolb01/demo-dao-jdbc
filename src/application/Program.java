package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
			
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		Seller seller1 = sellerDao.findById(3);
		System.out.println(seller1);
		
		List<Seller> sellers = sellerDao.findByDepartment(new Department(4, ""));
		
		for(int i=0; i<sellers.size(); i++)
		{
			System.out.println(sellers.get(i));
		}
	}

}
