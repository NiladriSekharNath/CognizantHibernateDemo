package com.cognizant.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cognizant.model.Employee;

public class ManageEmployee {

	private static SessionFactory factory;

	public static void main(String[] args) throws ParseException {

		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		ManageEmployee ME = new ManageEmployee();

		/* Add few employee records in database */
		Integer empID1 = ME.addEmployee("Zara", "Ali", new SimpleDateFormat("yyyy-mm-dd").parse("1998-04-02"));
		Integer empID2 = ME.addEmployee("Richa", "Ali", new SimpleDateFormat("yyyy-mm-dd").parse("1998-04-02"));
		Integer empID3 = ME.addEmployee("Rusha", "Ali", new SimpleDateFormat("yyyy-mm-dd").parse("1998-04-02"));

		/* List down all the employees */
		ME.listEmployees();

		/* Update employee's records */
		ME.updateEmployee(empID1, "Rudrash");

		/* Delete an employee from the database */
		ME.deleteEmployee(empID2);

		/* List down new list of the employees */
		ME.listEmployees();
	}

	/* Method to CREATE an employee in the database */
	public Integer addEmployee(String firstName, String lastName, Date date) {

		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;

		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(firstName,lastName,date);
			employeeID = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException | SecurityException | IllegalStateException e) {
			if (tx != null)
				try {
					tx.rollback();
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeID;
	}

	/* Method to READ all the employees */
	public void listEmployees() {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> employees = session.createQuery("FROM Employee").list();
			for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
				{
					Employee employee = (Employee) iterator.next();
					System.out.print(employee);
				}
				System.out.println();
			}
			try {
				tx.commit();
			} catch (SecurityException | IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (HibernateException e) {
			if (tx != null)
				try {
					tx.rollback();
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE salary for an employee */
	public void updateEmployee(Integer EmployeeID, String lastName) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, EmployeeID);
			employee.setLastName(lastName);
			session.update(employee);
			tx.commit();
		} catch (HibernateException | SecurityException | IllegalStateException e) {
			if (tx != null)
				try {
					tx.rollback();
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void deleteEmployee(Integer EmployeeID) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, EmployeeID);
			session.delete(employee);
			try {
				tx.commit();
			} catch (SecurityException | IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (HibernateException e) {
			if (tx != null)
				try {
					tx.rollback();
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}