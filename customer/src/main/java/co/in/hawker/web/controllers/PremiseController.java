package co.in.hawker.web.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.in.hawker.entities.Premise;
import co.in.hawker.services.PremiseService;
import co.in.hawker.util.FailureStatus;
import co.in.hawker.util.Status;
import co.in.hawker.util.SuccessStatus;

/**
 * @author Siva
 * 
 */
@Controller
@RequestMapping(value = "/premises/")
public class PremiseController extends MasterController {
	@Autowired
	private PremiseService premiseService;

	@InitBinder
	private void dataBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> addPremise(@RequestBody Premise premise) {

		try {
			Premise addedPremise = premiseService.addPremise(premise);

			Status body = new SuccessStatus("Order added Successfully !", addedPremise);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException constraintViolationException) {
			Status body = new FailureStatus("Failed to add Order", "Exception",
					constraintViolationException.getMessage(), premise);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (TransactionSystemException transactionSystemException) {

			Status body = new FailureStatus("Failed to add Order", "Exception", transactionSystemException.getMessage(),
					premise);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to add Order", "Exception", e.getMessage(), premise);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getAllOrders() {
		try {
			List<Premise> premises = premiseService.getAllPremises();

			Status body = new SuccessStatus("Orders fetched Successfully !", premises);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Order", "Exception", e.getMessage(), null);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

}