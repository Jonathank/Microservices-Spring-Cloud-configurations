/**
 * 
 */
package app.nanaBank.services.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import app.nanaBank.dto.CardsDTO;

/**
 *@author JONATHAN 
 */
@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value ="/api/cards/fetch", consumes ="application/json")
    public ResponseEntity<CardsDTO> fetchCardDetails(
    		@RequestHeader("nanabank-correlation-id")String correlationId,
	    @RequestParam String mobileNumber
	    ); 
}
