/**
 * 
 */
package app.nanaBank.services.lmp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import app.nanaBank.dto.AccountsDTO;
import app.nanaBank.dto.CardsDTO;
import app.nanaBank.dto.CustomerDTO;
import app.nanaBank.dto.CustomerDetailsDTO;
import app.nanaBank.dto.LoansDTO;
import app.nanaBank.exceptions.ResourceNotFoundException;
import app.nanaBank.mapper.AccountsMapper;
import app.nanaBank.mapper.CustomerMapper;
import app.nanaBank.model.Accounts;
import app.nanaBank.model.Customer;
import app.nanaBank.repository.AccountsRepository;
import app.nanaBank.repository.CustomerRepository;
import app.nanaBank.services.ICustomerService;
import app.nanaBank.services.client.CardsFeignClient;
import app.nanaBank.services.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;

/**
 * @author JONATHAN
 */
@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService{

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;
    
    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String phoneNumber,String correlationId) {
	Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
		.orElseThrow(() -> 
		 new ResourceNotFoundException("Customer","mobileNumber", phoneNumber));
		
		Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId())
			.orElseThrow(() -> 
			    new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
		CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
		customerDTO.setAccountsDetails(AccountsMapper.mapToAccountsDTO(account, new AccountsDTO()));
		 
		CustomerDetailsDTO customerDetailsDTO = CustomerMapper.mapToCustomerDetailsDTO(customer, new CustomerDetailsDTO());
		
		customerDetailsDTO.setAccountsDetails(AccountsMapper.mapToAccountsDTO(account, new AccountsDTO()));
		
		ResponseEntity<LoansDTO> loansDetailsEntity = loansFeignClient.fetchLoanDetails(correlationId,phoneNumber);
		
		if(null != loansDetailsEntity) {
			customerDetailsDTO.setLoansDetails(loansDetailsEntity.getBody());
		}
		
		ResponseEntity<CardsDTO> cardsDetailsEntity = cardsFeignClient.fetchCardDetails(correlationId,phoneNumber);
		if(null != cardsDetailsEntity) {
		customerDetailsDTO.setCardsDetails(cardsDetailsEntity.getBody());
		}
		
		return customerDetailsDTO;
    }

}
