/**
 * 
 */
package app.nanaBank.services;

import app.nanaBank.dto.CustomerDetailsDTO;

/**
 * @author JONATHAN
 */
public interface ICustomerService {
    						
    CustomerDetailsDTO fetchCustomerDetails(String phoneNumber,String correlationId);
}
