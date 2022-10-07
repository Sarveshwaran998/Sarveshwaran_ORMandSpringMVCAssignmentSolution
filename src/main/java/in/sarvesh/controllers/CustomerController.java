package in.sarvesh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sarvesh.models.Customer;
import in.sarvesh.services.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		List<Customer> theCustomers = customerService.findAll();
		theModel.addAttribute("Customers", theCustomers); 
		return "list-Customers";
	}
		
		@RequestMapping("/search")
		public String search(@RequestParam("f_name") String f_name, @RequestParam("l_name") String l_name, Model theModel) {

			// check names, if both are empty then just give list of all Customers

			if (f_name.trim().isEmpty() && l_name.trim().isEmpty()) {
				return "redirect:/customers/list";
			} else {
				// else, search by first name and last name
				List<Customer> theCustomers = customerService.searchBy(f_name, l_name);

				// add to the spring model
				theModel.addAttribute("Customers", theCustomers);

				// send to list-Customers
				return "list-Customers";
			}

		}
		
		@RequestMapping("/showFormForAdd")
		public String showFormForAdd(Model theModel) {

			// create model attribute to bind form data
			Customer theCustomer = new Customer();

			theModel.addAttribute("Customer", theCustomer);

			return "Customer-form";
		}

		@RequestMapping("/showFormForUpdate")
		public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

			// get the Customer from the service
			Customer theCustomer = customerService.findById(theId);

			// set Customer as a model attribute to pre-populate the form
			theModel.addAttribute("Customer", theCustomer);

			// send over to our form
			return "Customer-form";
		}

		@PostMapping("/save")
		public String saveCustomer(@RequestParam("id") int id, @RequestParam("f_name") String f_name,
				@RequestParam("l_name") String l_name, @RequestParam("email") String email) {

			System.out.println(id);
			Customer theCustomer;
			if (id != 0) {
				theCustomer = customerService.findById(id);
				theCustomer.setF_name(f_name);
				theCustomer.setL_name(l_name);
				theCustomer.setEmail(email);
			} else
				theCustomer = new Customer(f_name, l_name, email);
			// save the Customer
			customerService.save(theCustomer);

			// use a redirect to prevent duplicate submissions
			return "redirect:/customers/list";

		}

		@RequestMapping("/delete")
		public String delete(@RequestParam("customerId") int theId) {

			// delete the Customer
			customerService.deleteById(theId);

			// redirect to /Customers/list
			return "redirect:/customers/list";

		}

}
