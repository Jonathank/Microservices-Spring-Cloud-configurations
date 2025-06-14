package app.nanaBank.loans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.nanaBank.loans.constants.LoansConstants;
import app.nanaBank.loans.dto.LoansContactInfoDTO;
import app.nanaBank.loans.dto.LoansDTO;
import app.nanaBank.loans.responsedto.ErrorResponseDTO;
import app.nanaBank.loans.responsedto.ResponseDTPO;
import app.nanaBank.loans.services.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

/**
 * @Tag is used to group and document the REST APIs 
 * for loans management in NANA Bank.
 * * This controller provides endpoints for
 *  creating, retrieving, updating, and deleting customer loans.
 * 
 */
@Tag(
	name = "	CRUD REST APIs for Loans Management in NANA Bank", 
	description = "APIs for managing customer Loans in NANA Bank, "
		+ "including creation, retrieval, updating, and deletion of Loans."
)
@RestController
@RequestMapping(path="/api/loans", produces = (MediaType.APPLICATION_JSON_VALUE))
@Validated
public class LoansController {
    
    private ILoansService loansService;
 
    public LoansController (ILoansService loansService) {
	this.loansService = loansService;
    }
    @Value("${build.version}")
    private String buildVersion;
    
    @Autowired
    private Environment environment;
    
    @Autowired
    private  LoansContactInfoDTO loansContactInfoDTO;
    
    
    @Operation(
	    summary = "Create Loans REST API", 
	    description = "This endpoint allows you to create"
	    	+ " a new customer Loan in NANA Bank."
    )
    @ApiResponses({
	    @ApiResponse(
	    responseCode = "201", 
	    description = "HTTP Status CREATED"
	    ),
	    @ApiResponse(
	    responseCode = "500",
	    description = "Internal Server Error",
	    content = @Content(
		    schema = @Schema(implementation = ErrorResponseDTO.class))
	    )
    })
    @PostMapping("/create")
     public ResponseEntity<ResponseDTPO> createLoan(
	     @Valid@RequestParam
	     @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
	     String mobileNumber
	     ) {
	loansService.createLoan(mobileNumber);
	return ResponseEntity.status(HttpStatus.CREATED).body(
		    new ResponseDTPO(LoansConstants.STATUS_CODE_CREATED,
			    LoansConstants.Message_201));
	    }
    
    @Operation(
	    summary = "Fetch Loan Details REST API", 
	    description = "This endpoint allows you to fetch"
		    + " Loan details of a customer by their phone number."
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
               schema = @Schema(implementation = ErrorResponseDTO.class ))
	  )
	        })
    @PostMapping("/fetch/LoanDetails")
    public ResponseEntity<LoansDTO> fetchLoanDetails(
	    @Valid@RequestParam
	  @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
	  String mobileNumber
	    ) {
	LoansDTO fetchedLoanDetails = loansService.fetchLoanDetails(mobileNumber);
	return ResponseEntity.status(HttpStatus.OK).body(fetchedLoanDetails);
    }

    @Operation(
	    summary = "Update Loans Details REST API", 
	    description = "This endpoint allows you to update"
		    + " Loans details of a customer."
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
    @PutMapping("/update")
    public ResponseEntity<ResponseDTPO> updateLoanDetails(
	    @Valid@RequestBody LoansDTO loansDTO
	    ) {
	boolean isUpdated = loansService.updateLoan(loansDTO);
	if (isUpdated) {
	    return ResponseEntity.status(HttpStatus.OK).body(
		    new ResponseDTPO(LoansConstants.STATUS_CODE_SUCCESS,
			    LoansConstants.LOAN_UPDATED_SUCCESSFULLY));	
	} else {
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
		    new ResponseDTPO(LoansConstants.STATUS_CODE_FAILURE,
			   LoansConstants.Message_500));
	    
	}
    }
    
    @Operation(
	    summary = "Delete Loans REST API", 
	    description = "This endpoint allows you to delete"
		    + " a customer Loan in NANA Bank."
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
    @PostMapping("/delete")
    public ResponseEntity<ResponseDTPO> deleteLoan(
	    @Valid@RequestParam
	    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
	    String mobileNumber
	    ) {
	
	boolean isDeleted = loansService.deleteLoan(mobileNumber);
	if (isDeleted) {
	    return ResponseEntity.status(HttpStatus.OK).body(
		    new ResponseDTPO(LoansConstants.STATUS_CODE_SUCCESS,
			    LoansConstants.LOAN_DELETED_SUCCESSFULLY));
	    } else {
			    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
		    new ResponseDTPO(LoansConstants.STATUS_CODE_FAILURE,
			    LoansConstants.Message_500));
			    
	    }
    }
    
    @Operation(
	    summary = "Get build information", 
	    description = "This endpoint allows you to fetch"
		    + "build information that is deployed into loans microservice"
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
    @GetMapping("/build-info")
    public ResponseEntity<String>getBuildInfo(){
	return ResponseEntity.status(HttpStatus.OK)
		.body(buildVersion);
    }
    
    @Operation(
	    summary = "Get Java version", 
	    description = "This endpoint allows you to fetch"
		    + "Java versions details that is installed into loans microservice"
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
    @GetMapping("/java-version")
    public ResponseEntity<String>getJavaVersion(){
	return ResponseEntity.status(HttpStatus.OK)
		.body(environment.getProperty("JAVA_HOME"));
    }
    
    @Operation(
	    summary = "Get Contact Information", 
	    description = "This endpoint allows you to fetch"
		    + "contact info used into loas microservice"
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
    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDTO>getConatctInfo(){
	return ResponseEntity.status(HttpStatus.OK)
		.body(loansContactInfoDTO);
    }
}

