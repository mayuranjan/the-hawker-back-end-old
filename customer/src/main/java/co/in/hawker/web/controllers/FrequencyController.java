package co.in.hawker.web.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.in.hawker.entities.Frequency;
import co.in.hawker.services.CredentialService;
import co.in.hawker.services.FrequencyService;
import co.in.hawker.services.ProductService;
import co.in.hawker.util.FailureStatus;
import co.in.hawker.util.Status;
import co.in.hawker.util.SuccessStatus;

/**
 * @author Siva
 * 
 */
@Controller
@RequestMapping(value = "/frequencies/")
public class FrequencyController extends MasterController {
	@Autowired
	private FrequencyService frequencyService;
	@Autowired
	private CredentialService credentialService;
	@Autowired
	private ProductService productService;

	@InitBinder
	private void dataBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/createFrequency", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> addFrequency(@RequestBody Frequency frequency) {

		try {
			Frequency addedFrequency = frequencyService.addFrequency(frequency);

			Status body = new SuccessStatus("Frequency added Successfully !", addedFrequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException constraintViolationException) {
			Status body = new FailureStatus("Failed to add Frequency", "Exception",
					constraintViolationException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (TransactionSystemException transactionSystemException) {

			Status body = new FailureStatus("Failed to add Frequency", "Exception",
					transactionSystemException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to add Frequency", "Exception", e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getAllFrequencies() {
		try {
			List<Frequency> frequencies = frequencyService.getAllFrequencies();

			Status body = new SuccessStatus("Frequencies fetched Successfully !", frequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Frequency", "Exception", e.getMessage(), null);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByFrequencyCredential", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getFrequenciesByCredential(@RequestParam("frequency") Frequency frequency) {
		try {
			List<Frequency> frequencies = frequencyService.getFrequenciesByCredential(frequency.getCredential());

			Status body = new SuccessStatus("Frequency fetched by Frequency Successfully!", frequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Frequency by Credential", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByCredentialAndStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getFrequenciesByCredentialAndStatus(@RequestParam("frequency") Frequency frequency) {
		try {
			List<Frequency> frequencies = frequencyService
					.getFrequenciesByCredentialAndStatus(frequency.getCredential(), frequency.isActive());

			Status body = new SuccessStatus("Frequency fetched by Credential and Status Successfully!", frequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Frequency by Credential and Status", "Exception",
					e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByCredentialAndProduct", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getFrequenciesByCredentialAndProduct(@RequestParam("frequency") Frequency frequency) {
		try {
			List<Frequency> frequencies = frequencyService
					.getFrequenciesByCredentialAndProduct(frequency.getCredential(), frequency.getProduct());

			Status body = new SuccessStatus("Frequency fetched by Credential and Product Successfully!", frequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Frequency by Credential and Product", "Exception",
					e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByCredentialAndStartDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getFrequenciesByCredentialAndStartDate(
			@RequestParam("frequency") Frequency frequency) {
		try {
			List<Frequency> frequencies = frequencyService
					.getFrequenciesByCredentialAndStartDate(frequency.getCredential(), frequency.getStartDate());

			Status body = new SuccessStatus("Frequency fetched by Credential and Start Date Successfully!",
					frequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Frequency by Credential and Start Date", "Exception",
					e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByCredentialAndStartDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getFrequenciesByCredentialAndEndDate(@RequestParam("frequency") Frequency frequency) {
		try {
			List<Frequency> frequencies = frequencyService
					.getFrequenciesByCredentialAndEndDate(frequency.getCredential(), frequency.getEndDate());

			Status body = new SuccessStatus("Frequency fetched by Credential and End Date Successfully!", frequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Frequency by Credential and End Date", "Exception",
					e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByCredentialAndProductAndStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getFrequencyByCredentialAndProductAndStatus(
			@RequestParam("frequency") Frequency frequency) {
		try {
			Frequency fetchedFrequency = frequencyService.getFrequencyByCredentialAndProductAndStatus(
					frequency.getCredential(), frequency.getProduct(), frequency.isActive());

			Status body = new SuccessStatus("Frequency fetched by Credential, Product and Status Successfully!",
					fetchedFrequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Frequency by Credential, Product and Status", "Exception",
					e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByFrequencyId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getFrequencyByFrequencyId(@PathVariable("id") Long frequencyId) {
		try {
			Frequency frequency = frequencyService.getFrequencyByFrequencyId(frequencyId);

			Status body = new SuccessStatus("Frequency fetched by Frequency Id Successfully!", frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Frequency by Frequency Id.", "Exception", e.getMessage(),
					frequencyId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateByFrequencyId/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequencyByFrequencyId(@PathVariable("id") Long frequencyId,
			@RequestBody Frequency frequency) {
		frequency.setFrequencyId(frequencyId);

		try {
			Frequency updatedFrequency = frequencyService.updateFrequencyByFrequencyId(frequency);

			Status body = new SuccessStatus("Frequency updated Successfully !", updatedFrequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {
			Status body = new FailureStatus("Failed to update Frequency.", "Exception", e.getMessage(), frequencyId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesStatusByCredential/{credentialId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesStatusByCredential(@PathVariable("credentialId") Long credentialId,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));

		try {
			List<Frequency> updatedFrequencies = frequencyService.updateFrequenciesStatusByCredential(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesStartDateByCredential/{credentialId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesStartDateByCredential(
			@PathVariable("credentialId") Long credentialId, @RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));

		try {
			List<Frequency> updatedFrequencies = frequencyService.updateFrequenciesStartDateByCredential(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesEndDateByCredential/{credentialId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesEndDateByCredential(@PathVariable("credentialId") Long credentialId,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));

		try {
			List<Frequency> updatedFrequencies = frequencyService.updateFrequenciesEndDateByCredential(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesFrequencyTypeByCredential/{credentialId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesFrequencyTypeByCredential(
			@PathVariable("credentialId") Long credentialId, @RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));

		try {
			List<Frequency> updatedFrequencies = frequencyService.updateFrequenciesFrequencyTypeByCredential(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesStatusByCredentialAndStatus/{credentialId}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesStatusByCredentialAndStatus(
			@PathVariable("credentialId") Long credentialId, @PathVariable("status") boolean fStatus,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setActive(fStatus);

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesStatusByCredentialAndStatus(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesStartDateByCredentialAndStatus/{credentialId}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesStartDateByCredentialAndStatus(
			@PathVariable("credentialId") Long credentialId, @PathVariable("status") boolean fStatus,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setActive(fStatus);

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesStartDateByCredentialAndStatus(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesEndDateByCredentialAndStatus/{credentialId}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesEndDateByCredentialAndStatus(
			@PathVariable("credentialId") Long credentialId, @PathVariable("status") boolean fStatus,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setActive(fStatus);

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesEndDateByCredentialAndStatus(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesFrequencyTypeByCredentialAndStatus/{credentialId}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesFrequencyTypeByCredentialAndStatus(
			@PathVariable("credentialId") Long credentialId, @PathVariable("status") boolean fStatus,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setActive(fStatus);

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesFrequencyTypeByCredentialAndStatus(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesStatusByCredentialAndProduct/{credentialId}/{productId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesStatusByCredentialAndProduct(
			@PathVariable("credentialId") Long credentialId, @PathVariable("productId") Long productId,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setProduct(productService.getProductByProductId(productId));

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesStatusByCredentialAndProduct(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesStartDateByCredentialAndProduct/{credentialId}/{productId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesStartDateByCredentialAndProduct(
			@PathVariable("credentialId") Long credentialId, @PathVariable("productId") Long productId,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setProduct(productService.getProductByProductId(productId));

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesStartDateByCredentialAndProduct(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesEndDateByCredentialAndProduct/{credentialId}/{productId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesEndDateByCredentialAndProduct(
			@PathVariable("credentialId") Long credentialId, @PathVariable("productId") Long productId,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setProduct(productService.getProductByProductId(productId));

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesEndDateByCredentialAndProduct(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesFrequencyTypeByCredentialAndProduct/{credentialId}/{productId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesFrequencyTypeByCredentialAndProduct(
			@PathVariable("credentialId") Long credentialId, @PathVariable("productId") Long productId,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setProduct(productService.getProductByProductId(productId));

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesFrequencyTypeByCredentialAndProduct(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesStatusByCredentialAndStartDate/{credentialId}/{startDate}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesStatusByCredentialAndStartDate(
			@PathVariable("credentialId") Long credentialId, @PathVariable("startDate") Date startDate,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setStartDate(startDate);

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesStatusByCredentialAndStartDate(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesStartDateByCredentialAndStartDate/{credentialId}/{startDate}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesStartDateByCredentialAndStartDate(
			@PathVariable("credentialId") Long credentialId, @PathVariable("startDate") Date startDate,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setStartDate(startDate);

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesStartDateByCredentialAndStartDate(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesEndDateByCredentialAndStartDate/{credentialId}/{startDate}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesEndDateByCredentialAndStartDate(
			@PathVariable("credentialId") Long credentialId, @PathVariable("startDate") Date startDate,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setStartDate(startDate);

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesEndDateByCredentialAndStartDate(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesFrequencyTypeByCredentialAndStartDate/{credentialId}/{startDate}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesFrequencyTypeByCredentialAndStartDate(
			@PathVariable("credentialId") Long credentialId, @PathVariable("startDate") Date startDate,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setStartDate(startDate);

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesFrequencyTypeByCredentialAndStartDate(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequenciesStatusByCredentialAndEndDate/{credentialId}/{endDate}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequenciesStatusByCredentialAndEndDate(
			@PathVariable("credentialId") Long credentialId, @PathVariable("endDate") Date endDate,
			@RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setEndDate(endDate);

		try {
			List<Frequency> updatedFrequencies = frequencyService
					.updateFrequenciesStatusByCredentialAndEndDate(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateFrequencyByCredentialAndProductAndStatus/{credentialId}/{productId}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateFrequencyByCredentialAndProductAndStatus(
			@PathVariable("credentialId") Long credentialId, @PathVariable("productId") Long productId,
			@PathVariable("status") boolean fStatus, @RequestBody Frequency frequency) {
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setProduct(productService.getProductByProductId(productId));
		frequency.setActive(fStatus);

		try {
			Frequency updatedFrequencies = frequencyService.updateFrequencyByCredentialAndProductAndStatus(frequency);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedFrequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByFrequencyId/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteFrequencyByFrequencyId(@PathVariable("id") Long frequencyId) {
		Frequency frequency = new Frequency();
		frequency.setFrequencyId(frequencyId);

		try {
			frequencyService.deleteFrequencyByFrequencyId(frequency);

			Status body = new SuccessStatus("Frequency deleted Successfully !", frequencyId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Frequency.", "Exception", e.getMessage(), frequencyId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByCredential/{credentialId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteFrequenciesByCredential(@PathVariable("credentialId") Long credentialId) {
		Frequency frequency = new Frequency();
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));

		try {
			frequencyService.deleteFrequenciesByCredential(frequency);

			Status body = new SuccessStatus("Frequency deleted Successfully !", frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete frequency", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete frequency", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete frequency.", "Exception", rException.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Frequency.", "Exception", e.getMessage(),
					frequency.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByCredentialAndStatus/{credentialId}/{status}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteFrequenciesByCredentialAndStatus(
			@PathVariable("credentialId") Long credentialId, @PathVariable("status") boolean fStatus) {
		Frequency frequency = new Frequency();
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setActive(fStatus);

		try {
			frequencyService.deleteFrequenciesByCredential(frequency);

			Status body = new SuccessStatus("Frequency deleted Successfully !", frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete frequency", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete frequency", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete frequency.", "Exception", rException.getMessage(),
					frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Frequency.", "Exception", e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByCredentialAndProduct/{credentialId}/{productId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteFrequenciesByCredentialAndProduct(
			@PathVariable("credentialId") Long credentialId, @PathVariable("productId") long productId) {
		Frequency frequency = new Frequency();
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setProduct(productService.getProductByProductId(productId));

		try {
			frequencyService.deleteFrequenciesByCredentialAndProduct(frequency);

			Status body = new SuccessStatus("Frequency deleted Successfully !", frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete frequency", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete frequency", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete frequency.", "Exception", rException.getMessage(),
					frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Frequency.", "Exception", e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByCredentialAndStartDate/{credentialId}/{startDate}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteFrequenciesByCredentialAndStartDate(
			@PathVariable("credentialId") Long credentialId, @PathVariable("startDate") Date startDate) {
		Frequency frequency = new Frequency();
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setStartDate(startDate);

		try {
			frequencyService.deleteFrequenciesByCredentialAndStartDate(frequency);

			Status body = new SuccessStatus("Frequency deleted Successfully !", frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete frequency", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete frequency", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete frequency.", "Exception", rException.getMessage(),
					frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Frequency.", "Exception", e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByCredentialAndEndDate/{credentialId}/{endDate}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteFrequenciesByCredentialAndEndDate(
			@PathVariable("credentialId") Long credentialId, @PathVariable("endDate") Date endDate) {
		Frequency frequency = new Frequency();
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setEndDate(endDate);

		try {
			frequencyService.deleteFrequenciesByCredentialAndEndDate(frequency);

			Status body = new SuccessStatus("Frequency deleted Successfully !", frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete frequency", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete frequency", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete frequency.", "Exception", rException.getMessage(),
					frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Frequency.", "Exception", e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByCredentialAndProductAndStatus/{credentialId}/{productId}/{status}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteFrequencyByCredentialAndProductAndStatus(
			@PathVariable("credentialId") Long credentialId, @PathVariable("productId") Long productId,
			@PathVariable("status") boolean fStatus) {
		Frequency frequency = new Frequency();
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setProduct(productService.getProductByProductId(productId));
		frequency.setActive(fStatus);

		try {
			frequencyService.deleteFrequencyByCredentialAndProductAndStatus(frequency);

			Status body = new SuccessStatus("Frequency deleted Successfully !", frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete frequency", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete frequency", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete frequency.", "Exception", rException.getMessage(),
					frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Frequency.", "Exception", e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteFrequenciesByCredentialAndStartDateAndStatus/{credentialId}/{startDate}/{status}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteFrequenciesByCredentialAndStartDateAndStatus(
			@PathVariable("credentialId") Long credentialId, @PathVariable("startDate") Date startDate,
			@PathVariable("status") boolean fStatus) {
		Frequency frequency = new Frequency();
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setStartDate(startDate);
		frequency.setActive(fStatus);

		try {
			frequencyService.deleteFrequenciesByCredentialAndStartDateAndStatus(frequency);

			Status body = new SuccessStatus("Frequency deleted Successfully !", frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete frequency", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete frequency", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete frequency.", "Exception", rException.getMessage(),
					frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Frequency.", "Exception", e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteFrequenciesByCredentialAndEndDateAndStatus/{credentialId}/{endDate}/{status}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteFrequenciesByCredentialAndEndDateAndStatus(
			@PathVariable("credentialId") Long credentialId, @PathVariable("endDate") Date endDate,
			@PathVariable("status") boolean fStatus) {
		Frequency frequency = new Frequency();
		frequency.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		frequency.setEndDate(endDate);
		frequency.setActive(fStatus);

		try {
			frequencyService.deleteFrequenciesByCredentialAndEndDateAndStatus(frequency);

			Status body = new SuccessStatus("Frequency deleted Successfully !", frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete frequency", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete frequency", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete frequency.", "Exception", rException.getMessage(),
					frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Frequency.", "Exception", e.getMessage(), frequency);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

}