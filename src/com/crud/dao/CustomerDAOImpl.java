package com.crud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crud.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	
	// need to inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//create query
		Query<Customer> query = session.createQuery("from Customer order by firstName", Customer.class);
//		Query<Customer> query = session.createQuery("from Customer", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = query.getResultList();
		
		//return result
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		//get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// save customer in db
		session.saveOrUpdate(customer);		//---->	if id == null then save else update 
	}

	@Override
	public Customer getCustomer(int id) {

		//get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// retrieve from database using pk
		Customer customer = session.get(Customer.class, id);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		System.out.println(id+"================================================================");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();
	}
}
