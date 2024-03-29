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

import co.in.hawker.entities.OrderItem;
import co.in.hawker.services.OrderItemService;
import co.in.hawker.services.OrderService;
import co.in.hawker.services.ProductService;
import co.in.hawker.util.FailureStatus;
import co.in.hawker.util.Status;
import co.in.hawker.util.SuccessStatus;

/**
 * @author Siva
 * 
 */
@Controller
@RequestMapping(value = "/orderItems/")
public class OrderItemController extends MasterController {
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;

	@InitBinder
	private void dataBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/createOrderItem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> addOrderItem(@RequestBody OrderItem order) {

		try {
			OrderItem addedOrderItem = orderItemService.addOrderItem(order);

			Status body = new SuccessStatus("OrderItem added Successfully !", addedOrderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException constraintViolationException) {
			Status body = new FailureStatus("Failed to add OrderItem", "Exception",
					constraintViolationException.getMessage(), order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (TransactionSystemException transactionSystemException) {

			Status body = new FailureStatus("Failed to add OrderItem", "Exception",
					transactionSystemException.getMessage(), order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to add OrderItem", "Exception", e.getMessage(), order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getAllOrderItems() {
		try {
			List<OrderItem> orderItems = orderItemService.getAllOrderItems();

			Status body = new SuccessStatus("OrderItems fetched Successfully !", orderItems);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch OrderItem", "Exception", e.getMessage(), null);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByOrder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getOrderItemsByOrder(@RequestParam("orderItem") OrderItem orderItem) {
		try {
			List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(orderItem.getOrder());

			Status body = new SuccessStatus("OrderItem fetched by OrderItem Successfully!", orderItems);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch OrderItem by Order", "Exception", e.getMessage(),
					orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByProduct", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getOrderItemsByProduct(@RequestParam("orderItem") OrderItem orderItem) {
		try {
			List<OrderItem> orderItems = orderItemService.getOrderItemsByProduct(orderItem.getProduct());

			Status body = new SuccessStatus("OrderItem fetched by Product Successfully!", orderItems);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch OrderItem by Product", "Exception", e.getMessage(),
					orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getOrderItemsByStatus(@RequestParam("orderItem") OrderItem orderItem) {
		try {
			List<OrderItem> orderItems = orderItemService.getOrderItemsByStatus(orderItem.getStatus());

			Status body = new SuccessStatus("OrderItem fetched by Status Successfully!", orderItems);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch OrderItem by Status", "Exception", e.getMessage(),
					orderItem.getStatus());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByOrderAndStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getOrderItemsByCredentialAndStatus(@RequestParam("orderItem") OrderItem orderItem) {
		try {
			List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderAndStatus(orderItem.getOrder(),
					orderItem.getStatus());

			Status body = new SuccessStatus("OrderItem fetched by Order and Status Successfully!", orderItems);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch OrderItem by Order and Status", "Exception",
					e.getMessage(), orderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByOrderItemId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getOrderItemByOrderItemId(@PathVariable("id") Long orderId) {
		try {
			OrderItem order = orderItemService.getOrderItemByOrderItemId(orderId);

			Status body = new SuccessStatus("OrderItem fetched by OrderItem Id Successfully!", order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch OrderItem by OrderItem Id.", "Exception", e.getMessage(),
					orderId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateByOrderItemId/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateOrderItemByOrderItemId(@PathVariable("id") Long orderId,
			@RequestBody OrderItem order) {
		order.setOrderItemId(orderId);

		try {
			OrderItem updatedOrderItem = orderItemService.updateOrderItemByOrderItemId(order);

			Status body = new SuccessStatus("OrderItem updated Successfully !", updatedOrderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {
			Status body = new FailureStatus("Failed to update OrderItem.", "Exception", e.getMessage(), orderId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateOrderItemsStatusByOrder/{orderId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateOrderItemsStatusByOrder(@PathVariable("orderId") Long orderId,
			@RequestBody OrderItem orderItem) {
		orderItem.setOrder(orderService.getOrderByOrderId(orderId));

		try {
			List<OrderItem> updatedOrderItems = orderItemService.updateOrderItemsStatusByOrder(orderItem);

			Status body = new SuccessStatus("Order Item updated Successfully !", updatedOrderItems);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Order Item", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Order Item", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Order Item.", "RuntimeException", rException.getMessage(),
					orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Order Item.", "Exception", e.getMessage(),
					orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateOrderItemsStatusByProduct/{productId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateOrderItemsStatusByProduct(@PathVariable("productId") Long productId,
			@RequestBody OrderItem orderItem) {
		orderItem.setProduct(productService.getProductByProductId(productId));

		try {
			List<OrderItem> updatedOrderItems = orderItemService.updateOrderItemsStatusByProduct(orderItem);

			Status body = new SuccessStatus("Order Item updated Successfully !", updatedOrderItems);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Order Item", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Order Item", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Order Item.", "RuntimeException", rException.getMessage(),
					orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Order Item.", "Exception", e.getMessage(),
					orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateOrderItemStatusByProductAndOrder/{productId}/{orderId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateOrderItemStatusByProductAndOrder(@PathVariable("productId") Long productId,
			@PathVariable("orderId") Long orderId, @RequestBody OrderItem orderItem) {
		orderItem.setProduct(productService.getProductByProductId(productId));
		orderItem.setOrder(orderService.getOrderByOrderId(orderId));

		try {
			OrderItem updatedOrderItem = orderItemService.updateOrderItemStatusByOrderAndProduct(orderItem);

			Status body = new SuccessStatus("Order Item updated Successfully !", updatedOrderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Order Item", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), orderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Order Item", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), orderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Order Item.", "RuntimeException", rException.getMessage(),
					orderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Order Item.", "Exception", e.getMessage(), orderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateOrderItemsQuantityByOrder/{orderId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateOrderItemsQuantityByOrder(@PathVariable("orderId") Long orderId,
			@RequestBody OrderItem orderItem) {
		orderItem.setOrder(orderService.getOrderByOrderId(orderId));

		try {
			List<OrderItem> updatedOrderItems = orderItemService.updateOrderItemsQuantityByOrder(orderItem);

			Status body = new SuccessStatus("Order Item updated Successfully !", updatedOrderItems);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Order Item", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Order Item", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Order Item.", "RuntimeException", rException.getMessage(),
					orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Order Item.", "Exception", e.getMessage(),
					orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateOrderItemsQuantityByProduct/{productId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateOrderItemsQuantityByProduct(@PathVariable("productId") Long productId,
			@RequestBody OrderItem orderItem) {
		orderItem.setProduct(productService.getProductByProductId(productId));

		try {
			List<OrderItem> updatedOrderItems = orderItemService.updateOrderItemsQuantityByProduct(orderItem);

			Status body = new SuccessStatus("Order Item updated Successfully !", updatedOrderItems);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Order Item", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Order Item", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Order Item.", "RuntimeException", rException.getMessage(),
					orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Order Item.", "Exception", e.getMessage(),
					orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateOrderItemQuantityByProductAndOrder/{productId}/{orderId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateOrderItemQuantityByProductAndOrder(@PathVariable("productId") Long productId,
			@PathVariable("orderId") Long orderId, @RequestBody OrderItem orderItem) {
		orderItem.setProduct(productService.getProductByProductId(productId));
		orderItem.setOrder(orderService.getOrderByOrderId(orderId));

		try {
			OrderItem updatedOrderItem = orderItemService.updateOrderItemQuantityByOrderAndProduct(orderItem);

			Status body = new SuccessStatus("Order Item updated Successfully !", updatedOrderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Order Item", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), orderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Order Item", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), orderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Order Item.", "RuntimeException", rException.getMessage(),
					orderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Order Item.", "Exception", e.getMessage(), orderItem);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByOrderItemId/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteByOrderItemId(@PathVariable("id") Long orderId) {
		OrderItem order = new OrderItem();
		order.setOrderItemId(orderId);

		try {
			orderItemService.deleteOrderItemByOrderItemId(order);

			Status body = new SuccessStatus("OrderItem deleted Successfully !", orderId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete OrderItem.", "Exception", e.getMessage(), orderId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByOrder/{orderId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteByOrder(@PathVariable("orderId") Long orderId) {
		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(orderService.getOrderByOrderId(orderId));

		try {
			orderItemService.deleteOrderItemsByOrder(orderItem);

			Status body = new SuccessStatus("OrderItem deleted Successfully !", orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete order", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete order", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete order.", "Exception", rException.getMessage(),
					orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete OrderItem.", "Exception", e.getMessage(),
					orderItem.getOrder());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByProduct/{productId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteByProduct(@PathVariable("productId") Long productId) {
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(productService.getProductByProductId(productId));

		try {
			orderItemService.deleteOrderItemsByProduct(orderItem);

			Status body = new SuccessStatus("OrderItem deleted Successfully !", orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete order", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete order", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete order.", "Exception", rException.getMessage(),
					orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete OrderItem.", "Exception", e.getMessage(),
					orderItem.getProduct());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByStatus/{oStatus}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteBystatus(@PathVariable("oStatus") String oStatus) {
		OrderItem orderItem = new OrderItem();
		orderItem.setStatus(oStatus);

		try {
			orderItemService.deleteOrderItemsByProduct(orderItem);

			Status body = new SuccessStatus("OrderItem deleted Successfully !", orderItem.getStatus());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete order", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), orderItem.getStatus());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete order", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), orderItem.getStatus());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete order.", "Exception", rException.getMessage(),
					orderItem.getStatus());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete OrderItem.", "Exception", e.getMessage(),
					orderItem.getStatus());
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByOrderAndStatus/{orderId}/{status}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteOrderItemsByOrderAndStatus(@PathVariable("orderId") Long orderId,
			@PathVariable("status") String fStatus) {
		OrderItem order = new OrderItem();
		order.setOrder(orderService.getOrderByOrderId(orderId));
		order.setStatus(fStatus);

		try {
			orderItemService.deleteOrderItemsByOrderAndStatus(order);

			Status body = new SuccessStatus("OrderItem deleted Successfully !", order);
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

			Status body = new FailureStatus("Failed to delete OrderItem.", "Exception", e.getMessage(), order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByOrderAndStatus/{orderId}/{productId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteOrderItemByOrderAndProduct(@PathVariable("orderId") Long orderId,
			@PathVariable("productId") Long productId) {
		OrderItem order = new OrderItem();
		order.setOrder(orderService.getOrderByOrderId(orderId));
		order.setProduct(productService.getProductByProductId(productId));

		try {
			orderItemService.deleteOrderItemByOrderAndProduct(order);

			Status body = new SuccessStatus("OrderItem deleted Successfully !", order);
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

			Status body = new FailureStatus("Failed to delete OrderItem.", "Exception", e.getMessage(), order);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}
}