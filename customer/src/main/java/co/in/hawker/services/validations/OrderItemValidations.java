package co.in.hawker.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import co.in.hawker.entities.OrderItem;
import co.in.hawker.repositories.OrderItemRepository;
import co.in.hawker.util.Validation;

public abstract class OrderItemValidations {
	@Autowired
	private OrderItemRepository orderItemRespository;

	protected Validation validation;

	// check whose opposite is wrong
	protected Validation checkIfOrderItemIdDoesntExists(OrderItem orderItem) {
		Boolean isValid = orderItemRespository.findByOrderItemId(orderItem.getOrderItemId()) == null;
		String errorMessage = "OrderItem ID \"" + orderItem.getOrderItemId()
				+ "\" already exist, can't add/update OrderItem with same OrderItem ID.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfOrderItemIdExists(OrderItem orderItem) {
		Boolean isValid = orderItemRespository.findByOrderItemId(orderItem.getOrderItemId()) == null;
		String errorMessage = "OrderItem ID \"" + orderItem.getOrderItemId()
				+ "\" doesn't exist, can't delete OrderItem.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfOrderItemOrderExists(OrderItem orderItem) {
		Boolean isValid = orderItemRespository.findByOrder(orderItem.getOrder()) == null;
		String errorMessage = "Order Item with Order \"" + orderItem.getOrder()
				+ "\" doesn't exist, can't delete Order Item.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfOrderItemProductExists(OrderItem orderItem) {
		Boolean isValid = orderItemRespository.findByProduct(orderItem.getProduct()) == null;
		String errorMessage = "Order Item with Product \"" + orderItem.getProduct()
				+ "\" doesn't exist, can't delete Order Item.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfOrderItemStatusExists(OrderItem orderItem) {
		Boolean isValid = orderItemRespository.findByStatus(orderItem.getStatus()) == null;
		String errorMessage = "Order Item with Status \"" + orderItem.getStatus()
				+ "\" doesn't exist, can't delete Order Item.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfOrderItemOrderAndStatusExists(OrderItem orderItem) {
		Boolean isValid = orderItemRespository.findByOrderAndStatus(orderItem.getOrder(),
				orderItem.getStatus()) == null;
		String errorMessage = "Order Item with Order \"" + orderItem.getOrder() + "\" Status \"" + orderItem.getStatus()
				+ "\" doesn't exist, can't delete Order Item.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfOrderItemOrderAndProductExists(OrderItem orderItem) {
		Boolean isValid = orderItemRespository.findByOrderAndProduct(orderItem.getOrder(),
				orderItem.getProduct()) == null;
		String errorMessage = "Order Item with Order \"" + orderItem.getOrder() + "\" Product \""
				+ orderItem.getProduct() + "\" doesn't exist, can't delete Order Item.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected void handleException(Validation validation) throws RuntimeException {
		if (!validation.getIsValid()) {
			throw new RuntimeException(validation.getErrorMessage());
		}
	}
}