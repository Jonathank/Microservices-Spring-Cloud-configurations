/**
 * 
 */
package app.nanaBank.mapper;

import app.nanaBank.dto.CustomerDTO;
import app.nanaBank.dto.CustomerDetailsDTO;
import app.nanaBank.model.Customer;

/**
 * @author JONATHAN
 */
public class CustomerMapper {

    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDTO) {
	if (customer == null) {
	    return null;
	}
	if (customerDTO == null) {
	    customerDTO = new CustomerDTO();
	}
	
	customerDTO.setName(customer.getName());
	customerDTO.setEmail(customer.getEmail());
	customerDTO.setPhoneNumber(customer.getPhoneNumber());
	
	return customerDTO;
    }
    
    public static Customer mapToCustomer(CustomerDTO customerDTO, Customer customer) {
	if (customerDTO == null) {
	    return null;
	}
	if (customer == null) {
	    customer = new Customer();
	}
	customer.setName(customerDTO.getName());
	customer.setEmail(customerDTO.getEmail());
	customer.setPhoneNumber(customerDTO.getPhoneNumber());
	
	return customer;
    }
    
    public static CustomerDetailsDTO mapToCustomerDetailsDTO(Customer customer, CustomerDetailsDTO customerDetailsDTO) {
	if (customer == null) {
	    return null;
	}
	if (customerDetailsDTO == null) {
	    customerDetailsDTO = new CustomerDetailsDTO();
	}
	
	customerDetailsDTO.setName(customer.getName());
	customerDetailsDTO.setEmail(customer.getEmail());
	customerDetailsDTO.setPhoneNumber(customer.getPhoneNumber());
	
	return customerDetailsDTO;
    }
}
