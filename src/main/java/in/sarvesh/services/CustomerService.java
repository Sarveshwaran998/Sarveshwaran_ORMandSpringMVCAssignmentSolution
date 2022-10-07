package in.sarvesh.services;

import java.util.List;
import in.sarvesh.models.Customer;

public interface CustomerService {
	public List<Customer> findAll();
	Customer findById(int id);
	void save(Customer customer);
	void deleteById(int id);
	List<Customer> searchBy(String f_name, String l_name);
}
