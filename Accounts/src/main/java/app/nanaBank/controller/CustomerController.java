/**
 * 
 */
package app.nanaBank.controller;

import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import app.nanaBank.dto.CustomerDetailsDTO;
import app.nanaBank.responsedto.ErrorResponseDTO;
import app.nanaBank.services.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

/**
 * @author JONATHAN
 */
@Tag(
	name = "CRUD REST APIs for Customer Management in NANA Bank", 
	description = "APIs for fetching customer details."
)
@RestController
@RequestMapping(path="/api/customers", produces = (MediaType.APPLICATION_JSON_VALUE))
@Validated
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;
    
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Operation(
	    summary = "Fetch Customer Details REST API", 
	    description = "This endpoint allows you to fetch"
		    + " customer details by their phone number."
    )
    @ApiResponses({
	    @ApiResponse(
	    responseCode = "200", 
	    description = "HTTP Status OK"
	           ),
	    
	 @ApiResponse(
			    responseCode = "500", 
			    description = "Internal Server Error",
			    content = @Content(
				schema = @Schema(
				implementation = ErrorResponseDTO.class
			))
    	    )
    })
    @GetMapping("/fetch/details")
    public ResponseEntity<CustomerDetailsDTO>fetchCustomerDetails(
    		@RequestHeader("nanabank-correlation-id")String correlationId,
	    @RequestParam
	    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	    String phoneNumber
	    ){
    	logger.debug("nanaBank-correlation-id found :  {}", correlationId);
	CustomerDetailsDTO customerDetailsDTO = customerService.fetchCustomerDetails(phoneNumber,correlationId);
	return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailsDTO);
    }
}
