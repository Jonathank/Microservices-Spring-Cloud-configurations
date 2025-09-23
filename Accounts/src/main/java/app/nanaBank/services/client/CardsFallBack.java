package app.nanaBank.services.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import app.nanaBank.dto.CardsDTO;

@Component
public class CardsFallBack implements CardsFeignClient{

	@Override
	public ResponseEntity<CardsDTO> fetchCardDetails(String correlationId, String mobileNumber) {
		
		return null;
	}

	

}
