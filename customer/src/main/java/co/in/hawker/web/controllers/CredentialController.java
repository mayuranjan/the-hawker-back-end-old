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

import co.in.hawker.entities.Credential;
import co.in.hawker.services.CredentialService;
import co.in.hawker.util.FailureStatus;
import co.in.hawker.util.Status;
import co.in.hawker.util.SuccessStatus;

/**
 * @author Siva
 * 
 */
@Controller
@RequestMapping(value = "/credentials/")
public class CredentialController extends MasterController {
	@Autowired
	private CredentialService credentialService;

	@InitBinder
	private void dataBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/createCredential", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> addCredential(@RequestBody Credential credential) {

		try {
			Credential addedCredential = credentialService.addCredential(credential);

			Status body = new SuccessStatus("Credential added Successfully !", addedCredential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException constraintViolationException) {
			Status body = new FailureStatus("Failed to add Credential", "Exception",
					constraintViolationException.getMessage(), credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (TransactionSystemException transactionSystemException) {

			Status body = new FailureStatus("Failed to add Credential", "Exception",
					transactionSystemException.getMessage(), credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to add Credential", "Exception", e.getMessage(), credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/validateCredentialByUsername", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> validateCredentialByUsername(@RequestBody Credential credential) {

		try {
			if (credentialService.validateUsernameAndPassword(credential)) {
				Status body = new SuccessStatus("Credential validated Successfully !", true);
				MultiValueMap<String, String> headers = new HttpHeaders();
				HttpStatus status = HttpStatus.OK;

				return new ResponseEntity<Status>(body, headers, status);
			} else {
				Status body;
				if (credentialService.getCredentialByUsername(credential.getUsername()) == null) {
					body = new FailureStatus("Incorrect Credential", "Exception", "Incorrect Username", false);
				} else {
					body = new FailureStatus("Incorrect Credential", "Exception", "Incorrect Passowrd", false);
				}

				MultiValueMap<String, String> headers = new HttpHeaders();
				HttpStatus status = HttpStatus.BAD_REQUEST;

				return new ResponseEntity<Status>(body, headers, status);
			}
		} catch (ConstraintViolationException constraintViolationException) {
			Status body = new FailureStatus("Incorrect Credential", "Exception",
					constraintViolationException.getMessage(), credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (TransactionSystemException transactionSystemException) {
			Status body = new FailureStatus("Incorrect Credential", "Exception",
					transactionSystemException.getMessage(), credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {
			Status body = new FailureStatus("Incorrect Credential", "Exception", e.getMessage(), credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/validateCredentialByMobileNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> validateCredentialByMobileNumber(@RequestBody Credential credential) {

		try {
			if (credentialService.validateMobileNumberAndPassword(credential)) {
				Status body = new SuccessStatus("Credential validated Successfully !", true);
				MultiValueMap<String, String> headers = new HttpHeaders();
				HttpStatus status = HttpStatus.OK;

				return new ResponseEntity<Status>(body, headers, status);
			} else {
				Status body;
				if (credentialService.getCredentialByMobileNumber(credential.getMobileNumber()) == null) {
					body = new FailureStatus("Incorrect Credential", "Exception", "Incorrect Mobile Number", false);
				} else {
					body = new FailureStatus("Incorrect Credential", "Exception", "Incorrect Passowrd", false);
				}

				MultiValueMap<String, String> headers = new HttpHeaders();
				HttpStatus status = HttpStatus.BAD_REQUEST;

				return new ResponseEntity<Status>(body, headers, status);
			}
		} catch (ConstraintViolationException constraintViolationException) {
			Status body = new FailureStatus("Incorrect Credential", "Exception",
					constraintViolationException.getMessage(), credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (TransactionSystemException transactionSystemException) {
			Status body = new FailureStatus("Incorrect Credential", "Exception",
					transactionSystemException.getMessage(), credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {
			Status body = new FailureStatus("Incorrect Credential", "Exception", e.getMessage(), credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/validateCredentialByEmailId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> validateCredentialByEmailId(@RequestBody Credential credential) {

		try {
			if (credentialService.validateEmailIdAndPassword(credential)) {
				Status body = new SuccessStatus("Credential validated Successfully !", true);
				MultiValueMap<String, String> headers = new HttpHeaders();
				HttpStatus status = HttpStatus.OK;

				return new ResponseEntity<Status>(body, headers, status);
			} else {
				Status body;
				if (credentialService.getCredentialByEmailId(credential.getEmailId()) == null) {
					body = new FailureStatus("Incorrect Credential", "Exception", "Incorrect Email ID", false);
				} else {
					body = new FailureStatus("Incorrect Credential", "Exception", "Incorrect Passowrd", false);
				}

				MultiValueMap<String, String> headers = new HttpHeaders();
				HttpStatus status = HttpStatus.BAD_REQUEST;

				return new ResponseEntity<Status>(body, headers, status);
			}
		} catch (ConstraintViolationException constraintViolationException) {
			Status body = new FailureStatus("Incorrect Credential", "Exception",
					constraintViolationException.getMessage(), credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (TransactionSystemException transactionSystemException) {
			Status body = new FailureStatus("Incorrect Credential", "Exception",
					transactionSystemException.getMessage(), credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {
			Status body = new FailureStatus("Incorrect Credential", "Exception", e.getMessage(), credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getAllCredentials() {
		try {
			List<Credential> credentials = credentialService.getAllCredentials();

			Status body = new SuccessStatus("Credentials fetched Successfully !", credentials);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Credential", "Exception", e.getMessage(), null);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByCredentialUsername", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getCredentialByUsername(@RequestParam("username") String username) {
		try {
			Credential credential = credentialService.getCredentialByUsername(username);

			Status body = new SuccessStatus("Credential fetched by Username Successfully!", credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Credential by Username", "Exception", e.getMessage(),
					username);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByCredentialMobileNumber", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getCredentialByMobileNumber(@RequestParam("mobileNumber") int mobileNumber) {
		try {
			Credential credential = credentialService.getCredentialByMobileNumber(mobileNumber);

			Status body = new SuccessStatus("Credential fetched by Mobile Number Successfully!", credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Credential by Mobile Number", "Exception", e.getMessage(),
					mobileNumber);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByCredentialEmailId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getCredentialByEmailId(@RequestParam("emailId") String emailId) {
		try {
			Credential credential = credentialService.getCredentialByEmailId(emailId);

			Status body = new SuccessStatus("Credential fetched by Email Id Successfully!", credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Credential by Email Id", "Exception", e.getMessage(),
					emailId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByCredentialId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getCredentialByCredentialId(@PathVariable("id") Long credentialId) {
		try {
			Credential credential = credentialService.getCredentialByCredentialId(credentialId);

			Status body = new SuccessStatus("Credential fetched by Credential Id Successfully!", credential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Credential by Credential Id.", "Exception", e.getMessage(),
					credentialId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateByCredentialId/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateCredentialByCredentialId(@PathVariable("id") Long credentialId,
			@RequestBody Credential credential) {
		credential.setCredentialId(credentialId);

		try {
			Credential updatedCredential = credentialService.updateCredentialByCredentialId(credential);

			Status body = new SuccessStatus("Credential updated Successfully !", updatedCredential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {
			Status body = new FailureStatus("Failed to update Credential.", "Exception", e.getMessage(), credentialId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateByCredentialMobileNumber/{mobileNumber}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateCredentialByMobileNumber(@PathVariable("mobileNumber") int mobileNumber,
			@RequestBody Credential credential) {
		credential.setMobileNumber(mobileNumber);

		try {
			Credential updatedCredential = credentialService.updateCredentialByMobileNumber(credential);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedCredential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), mobileNumber);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), mobileNumber);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					mobileNumber);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(), mobileNumber);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateByCredentialEmailId/{emailId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateCredentialByEmailId(@PathVariable("emailId") String emailId,
			@RequestBody Credential credential) {
		credential.setEmailId(emailId);

		try {
			Credential updatedCredential = credentialService.updateCredentialByEmailId(credential);

			Status body = new SuccessStatus("Email Id updated Successfully !", updatedCredential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Email Id", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), emailId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Email Id", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), emailId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Email Id.", "RuntimeException", rException.getMessage(),
					emailId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Email Id.", "Exception", e.getMessage(), emailId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateByCredentialUsername/{username}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateCredentialByUsername(@PathVariable("username") String username,
			@RequestBody Credential credential) {
		credential.setUsername(username);

		try {
			Credential updatedCredential = credentialService.updateCredentialByUsername(credential);

			Status body = new SuccessStatus("Username updated Successfully !", updatedCredential);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Username", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), username);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Username", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), username);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Username.", "RuntimeException", rException.getMessage(),
					username);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Username.", "Exception", e.getMessage(), username);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByCredentialId/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteCredentialByCredentialId(@PathVariable("id") Long credentialId) {
		Credential credential = new Credential();
		credential.setCredentialId(credentialId);

		try {
			credentialService.deleteCredentialByCredentialId(credential);

			Status body = new SuccessStatus("Credential deleted Successfully !", credentialId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Credential.", "Exception", e.getMessage(), credentialId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByCredentialUsername/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteCredentialByCredentialUsername(@PathVariable("username") String username) {
		Credential credential = new Credential();
		credential.setUsername(username);

		try {
			credentialService.deleteCredentialByUsername(credential);

			Status body = new SuccessStatus("Credential deleted Successfully !", username);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete Credential", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), username);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete Credential", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), username);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete Credential.", "Exception", rException.getMessage(),
					username);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Credential.", "Exception", e.getMessage(), username);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByCredentialMobileNumber/{mobileNumber}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteCredentialByMobileNumber(@PathVariable("mobileNumber") int mobileNumber) {
		Credential credential = new Credential();
		credential.setMobileNumber(mobileNumber);

		try {
			credentialService.deleteCredentialByMobileNumber(credential);

			Status body = new SuccessStatus("Credential deleted Successfully !", mobileNumber);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete Credential", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), mobileNumber);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete Credential", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), mobileNumber);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete Credential.", "Exception", rException.getMessage(),
					mobileNumber);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Credential.", "Exception", e.getMessage(), mobileNumber);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByCredentialEmailId/{emailId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteCredentialByEmailId(@PathVariable("emailId") String emailId) {
		Credential credential = new Credential();
		credential.setEmailId(emailId);

		try {
			credentialService.deleteCredentialByEmailId(credential);

			Status body = new SuccessStatus("Credential deleted Successfully !", emailId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete Credential", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), emailId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete Credential", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), emailId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete Credential.", "Exception", rException.getMessage(),
					emailId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Credential.", "Exception", e.getMessage(), emailId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

}