package in.sarvesh.services;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.sarvesh.models.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService{
	
	private SessionFactory sessionFactory;
	private Session session;
	
	@Autowired
	public CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		}
		catch(HibernateException e) {
			session = sessionFactory.openSession();
		}
		
	}

	@Transactional
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		
		List<Customer> customers = session.createQuery("from Customer").list();
		tx.commit();
		return customers;
	}

	@Transactional
	public Customer findById(int id) {
		Customer customer = new Customer();

		
		Transaction tx = session.beginTransaction();

		// find record with Id from the database table
		customer = session.get(Customer.class, id);

		tx.commit();

		return customer;
	}

	@Transactional
	public void save(Customer theCustomer) {
		Transaction tx = session.beginTransaction();

		// save transaction
		session.saveOrUpdate(theCustomer);

		tx.commit();
		
	}

	@Transactional
	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();

		// get transaction
		Customer customer = session.get(Customer.class, id);

		// delete record
		session.delete(customer);

		tx.commit();
		
	}

	@Transactional
	public List<Customer> searchBy(String f_name, String l_name) {
		Transaction tx = session.beginTransaction();
		String query = "";
		if (f_name.length() != 0 && l_name.length() != 0)
			query = "from Customer where f_name like '%" + f_name + "%' or l_name like '%" + l_name + "%'";
		else if (f_name.length() != 0)
			query = "from Customer where f_name like '%" + f_name + "%'";
		else if (l_name.length() != 0)
			query = "from Customer where l_name like '%" + l_name + "%'";
		else
			System.out.println("Cannot search without input data");

		List<Customer> customer = session.createQuery(query).list();

		tx.commit();

		return customer;
	}
	// print the loop
	@Transactional
	public void print(List<Customer> customer) {

		for (Customer b : customer) {
			System.out.println(b);
		}
	}
}
