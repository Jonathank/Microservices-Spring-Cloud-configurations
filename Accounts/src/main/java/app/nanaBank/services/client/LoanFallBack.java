package app.nanaBank.services.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import app.nanaBank.dto.LoansDTO;

@Component
public class LoanFallBack implements LoansFeignClient{

	@Override
	public ResponseEntity<LoansDTO> fetchLoanDetails(String correlationId, String mobileNumber) {
		
		return null;
	}

}
