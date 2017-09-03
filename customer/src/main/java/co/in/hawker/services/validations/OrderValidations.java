package co.in.hawker.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import co.in.hawker.entities.Order;
import co.in.hawker.repositories.OrderRepository;
import co.in.hawker.util.Validation;

public abstract class OrderValidations {
	@Autowired
	private OrderRepository orderRespository;

	protected Validation validation;

	// check whose opposite is wrong
	protected Validation checkIfOrderIdDoesntExists(Order order) {
		Boolean isValid = orderRespository.findByOrderId(order.getOrderId()) == null;
		String errorMessage = "Order ID \"" + order.getOrderId()
				+ "\" already exist, can't add/update Order with same Order ID.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfOrderIdExists(Order order) {
		Boolean isValid = orderRespository.findByOrderId(order.getOrderId()) == null;
		String errorMessage = "Order ID \"" + order.getOrderId()
				+ "\" doesn't exist, can't delete Order.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfOrderCredentialExists(Order order) {
		Boolean isValid = orderRespository.findByCredential(order.getCredential()) == null;
		String errorMessage = "Order with Credential \"" + order.getCredential()
				+ "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfOrderCredentialAndStatusExists(Order order) {
		Boolean isValid = orderRespository.findByCredentialAndStatus(order.getCredential(),
				order.getStatus()) == null;
		String errorMessage = "Order with Credential \"" + order.getCredential().getCredentialId() + "\" and Status \""
				+ order.getStatus() + "\" doesn't exist, can't delete Order.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}
	
	
	protected void handleException(Validation validation) throws RuntimeException {
		if (!validation.getIsValid()) {
			throw new RuntimeException(validation.getErrorMessage());
		}
	}
}