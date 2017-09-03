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

import co.in.hawker.entities.Order;
import co.in.hawker.services.CredentialService;
import co.in.hawker.services.OrderService;
import co.in.hawker.util.FailureStatus;
import co.in.hawker.util.Status;
import co.in.hawker.util.SuccessStatus;

/**
 * @author Siva
 * 
 */
@Controller
@RequestMapping(value = "/orders/")
public class OrderController extends MasterController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CredentialService credentialService;

	@InitBinder
	private void dataBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> addOrder(@RequestBody Order order) {

		try {
			Order addedOrder = orderService.addOrder(order);

			Status body = new SuccessStatus("Order added Successfully !", addedOrder);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException constraintViolationException) {
			Status body = new FailureStatus("Failed to add Order", "Exception",
					constraintViolationException.getMessage(), order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (TransactionSystemException transactionSystemException) {

			Status body = new FailureStatus("Failed to add Order", "Exception", transactionSystemException.getMessage(),
					order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to add Order", "Exception", e.getMessage(), order);
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
			List<Order> frequencies = orderService.getAllOrders();

			Status body = new SuccessStatus("Orders fetched Successfully !", frequencies);
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

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByOrderCredential", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getOrdersByCredential(@RequestParam("order") Order order) {
		try {
			List<Order> frequencies = orderService.getOrdersByCredential(order.getCredential());

			Status body = new SuccessStatus("Order fetched by Order Successfully!", frequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Order by Credential", "Exception", e.getMessage(),
					order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByCredentialAndStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getOrdersByCredentialAndStatus(@RequestParam("order") Order order) {
		try {
			List<Order> frequencies = orderService.getOrdersByCredentialAndStatus(order.getCredential(),
					order.getStatus());

			Status body = new SuccessStatus("Order fetched by Credential and Status Successfully!", frequencies);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Order by Credential and Status", "Exception",
					e.getMessage(), order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByOrderId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getOrderByOrderId(@PathVariable("id") Long orderId) {
		try {
			Order order = orderService.getOrderByOrderId(orderId);

			Status body = new SuccessStatus("Order fetched by Order Id Successfully!", order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Order by Order Id.", "Exception", e.getMessage(), orderId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateByOrderId/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateOrderByOrderId(@PathVariable("id") Long orderId, @RequestBody Order order) {
		order.setOrderId(orderId);

		try {
			Order updatedOrder = orderService.updateOrderByOrderId(order);

			Status body = new SuccessStatus("Order updated Successfully !", updatedOrder);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {
			Status body = new FailureStatus("Failed to update Order.", "Exception", e.getMessage(), orderId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateOrdersStatusByCredential/{credentialId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateOrdersStatusByCredential(@PathVariable("credentialId") Long credentialId,
			@RequestBody Order order) {
		order.setCredential(credentialService.getCredentialByCredentialId(credentialId));

		try {
			List<Order> updatedOrders = orderService.updateOrdersStatusByCredential(order);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedOrders);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateOrdersStatusByCredentialAndStatus/{credentialId}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateOrdersStatusByCredentialAndStatus(
			@PathVariable("credentialId") Long credentialId, @PathVariable("status") String fStatus,
			@RequestBody Order order) {
		order.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		order.setStatus(fStatus);

		try {
			List<Order> updatedOrders = orderService.updateOrdersStatusByCredentialAndStatus(order);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedOrders);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(),
					order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByOrderId/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteOrderByOrderId(@PathVariable("id") Long orderId) {
		Order order = new Order();
		order.setOrderId(orderId);

		try {
			orderService.deleteOrderByOrderId(order);

			Status body = new SuccessStatus("Order deleted Successfully !", orderId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Order.", "Exception", e.getMessage(), orderId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByCredential/{credentialId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteOrdersByCredential(@PathVariable("credentialId") Long credentialId) {
		Order order = new Order();
		order.setCredential(credentialService.getCredentialByCredentialId(credentialId));

		try {
			orderService.deleteOrdersByCredential(order);

			Status body = new SuccessStatus("Order deleted Successfully !", order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete order", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete order", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete order.", "Exception", rException.getMessage(),
					order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Order.", "Exception", e.getMessage(),
					order.getCredential());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByCredentialAndStatus/{credentialId}/{status}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteOrdersByCredentialAndStatus(@PathVariable("credentialId") Long credentialId,
			@PathVariable("status") String fStatus) {
		Order order = new Order();
		order.setCredential(credentialService.getCredentialByCredentialId(credentialId));
		order.setStatus(fStatus);

		try {
			orderService.deleteOrdersByCredential(order);

			Status body = new SuccessStatus("Order deleted Successfully !", order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete order", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete order", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete order.", "Exception", rException.getMessage(), order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Order.", "Exception", e.getMessage(), order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}
}