package model.entities;

import java.util.Date;
import java.util.Objects;

public class Seller {
	private int Id;
	private String name;
	private String email;
	private Date birthdate;
	private double baseSalary;
	private Department department;
	
	public Seller()
	{
		
	}

	public Seller(int id, String name, String email, Date birthdate, double baseSalary, Department department) {
		super();
		Id = id;
		this.name = name;
		this.email = email;
		this.birthdate = birthdate;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Id == other.Id;
	}

	@Override
	public String toString() {
		return "Seller [Id=" + Id + ", name=" + name + ", email=" + email + ", birthdate=" + birthdate + ", baseSalary="
				+ baseSalary + ", department=" + department + "]";
	}
	
	
}
