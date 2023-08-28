package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Seller seller1 = sellerDao.findById(3);
//		System.out.println(seller1);

//		List<Seller> sellers = sellerDao.findByDepartment(new Department(4, ""));
//		List<Seller> sellers = sellerDao.findAll();
//		for (int i = 0; i < sellers.size(); i++) {
//			System.out.println(sellers.get(i));
//		}
		
		Department dep = new Department(2,"Electronics");
		try {
			Seller sel = new Seller(13, "Albert Red", "albertred@gmail.com", sdf.parse("08/08/1990") ,230.0, dep);
			sellerDao.update(sel);

		} catch (ParseException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		}
	}

}
