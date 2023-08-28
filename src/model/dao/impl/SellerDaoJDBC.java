package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection _conn) {
		this.conn = _conn;
	}

	@Override
	public void insert(Seller seller) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
	    try {
			st = conn.prepareStatement("INSERT INTO seller "
					+ "(name, email, birthdate, basesalary, departmentid) "
					+ "values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, seller.getName());
			st.setString(2, seller.getEmail());
			st.setDate(3, new java.sql.Date(seller.getBirthdate().getTime()));
			st.setDouble(4, seller.getBaseSalary());
			st.setInt(5, seller.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0)
			{
				rs = st.getGeneratedKeys();
				while(rs.next())
				{
					int id = rs.getInt(1);
					seller.setId(id);
					System.out.println("New entry inserted with Id " + id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		}
	    finally
	    {
	    	DB.closeStatement(st);
	    	DB.closeResultSet(rs);
	    }
	}

	@Override
	public void update(Seller seller) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("UPDATE seller "
					+"SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, seller.getName());
			st.setString(2, seller.getEmail());
			st.setDate(3, new java.sql.Date(seller.getBirthdate().getTime()));
			st.setDouble(4,  seller.getBaseSalary());
			st.setInt(5, seller.getDepartment().getId());
			st.setInt(6, seller.getId());
			st.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON Seller.DepartmentId = department.Id " + "where seller.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				return instantiateSeller(rs, dep);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return null;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department ON seller.DepartmentId = department.Id "
					+ "ORDER BY Id");
			
			rs = st.executeQuery();
			
			List<Seller> sellers = new ArrayList<Seller>();
			Department dep = null;
			while(rs.next())
			{
				if(dep == null)
					dep = instantiateDepartment(rs);
				sellers.add(instantiateSeller(rs, dep));
			}
			
			return sellers;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department ON seller.DepartmentId = department.Id "
					+ "WHERE department.Id = ? " + "ORDER BY Id");

			st.setInt(1, department.getId());
			rs = st.executeQuery();

			List<Seller> sellers = new ArrayList<>();
			Department dep = null; //might fail 11:48 283

			while (rs.next()) {
				if (dep == null)
					dep = instantiateDepartment(rs);
				sellers.add(instantiateSeller(rs, dep));
			}

			return sellers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		return new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate"),
				rs.getDouble("BaseSalary"), dep);
	}
}
