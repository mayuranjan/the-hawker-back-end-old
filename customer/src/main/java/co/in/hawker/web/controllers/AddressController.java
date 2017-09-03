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

import co.in.hawker.entities.Address;
import co.in.hawker.entities.Credential;
import co.in.hawker.services.AddressService;
import co.in.hawker.util.FailureStatus;
import co.in.hawker.util.Status;
import co.in.hawker.util.SuccessStatus;

/**
 * @author Siva
 * 
 */
@Controller
@RequestMapping(value = "/addresses/")
public class AddressController extends MasterController {
	@Autowired
	private AddressService addressService;

	@InitBinder
	private void dataBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/createAddress", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> addAddress(@RequestBody Address address) {

		try {
			Address addedAddress = addressService.addAddress(address);

			Status body = new SuccessStatus("Address added Successfully !", addedAddress);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException constraintViolationException) {
			Status body = new FailureStatus("Failed to add Address", "Exception",
					constraintViolationException.getMessage(), address);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (TransactionSystemException transactionSystemException) {

			Status body = new FailureStatus("Failed to add Address", "Exception",
					transactionSystemException.getMessage(), address);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to add Address", "Exception", e.getMessage(), address);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getAllAddresses() {
		try {
			List<Address> addresses = addressService.getAllAddresses();

			Status body = new SuccessStatus("Addresses fetched Successfully !", addresses);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Address", "Exception", e.getMessage(), null);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByCredential", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getAddressesByCredential(@RequestParam("credential") Credential credential) {
		try {
			List<Address> addresses = addressService.getAddressesByCredential(credential);

			Status body = new SuccessStatus("Address fetched by Credential Successfully!", addresses);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Address by Credential", "Exception", e.getMessage(),
					credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByAddressId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getAddressByAddressId(@PathVariable("id") Long addressId) {
		try {
			Address address = addressService.getAddressByAddressId(addressId);

			Status body = new SuccessStatus("Address fetched by Address Id Successfully!", address);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Address by Address Id.", "Exception", e.getMessage(),
					addressId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateByAddressId/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateAddressByAddressId(@PathVariable("id") Long addressId,
			@RequestBody Address address) {
		address.setAddressId(addressId);

		try {
			Address updatedAddress = addressService.updateAddressByAddressId(address);

			Status body = new SuccessStatus("Address updated Successfully !", updatedAddress);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {
			Status body = new FailureStatus("Failed to update Address.", "Exception", e.getMessage(), addressId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByAddressId/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteAddressByAddressId(@PathVariable("id") Long addressId) {
		Address address = new Address();
		address.setAddressId(addressId);

		try {
			addressService.deleteAddressByAddressId(address);

			Status body = new SuccessStatus("Address deleted Successfully !", addressId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Address.", "Exception", e.getMessage(), addressId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByAddressCredential/{credential}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteAddressByCredential(@PathVariable("credential") Credential credential) {
		Address address = new Address();
		address.setCredential(credential);

		try {
			addressService.deleteAddressesByCredential(address);

			Status body = new SuccessStatus("Address deleted Successfully !", address);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete Address", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), address);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete Address", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), address);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete Address.", "Exception", rException.getMessage(), address);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Address.", "Exception", e.getMessage(), address);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

}