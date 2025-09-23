/**
 * 
 */
package app.nanaBank.services.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import app.nanaBank.dto.LoansDTO;

/**
 *@author JONATHAN 
 */
@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping("/api/loans/fetch/LoanDetails")
    public ResponseEntity<LoansDTO> fetchLoanDetails(
    		@RequestHeader("nanabank-correlation-id")String correlationId,
	    @RequestParam String mobileNumber
	    ); 
}
