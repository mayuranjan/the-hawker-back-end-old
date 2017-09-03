package co.in.hawker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.entities.Credential;
import co.in.hawker.entities.Order;
import co.in.hawker.repositories.OrderRepository;
import co.in.hawker.services.validations.OrderValidations;

@Service
@Transactional
public class OrderService extends OrderValidations {
	// public class CredentialService extends CredentialValidations {
	@Autowired
	private OrderRepository orderRespository;

	public Order addOrder(Order order) {

		// duplicate check
		handleException(checkIfOrderIdDoesntExists(order));
		return orderRespository.save(order);
	}

	public List<Order> getAllOrders() {
		return orderRespository.findAll();
	}

	public Order getOrderByOrderId(Long orderId) {
		Order order = new Order();
		order.setOrderId(orderId);

		handleException(checkIfOrderIdDoesntExists(order));
		return orderRespository.findByOrderId(orderId);
	}

	public List<Order> getOrdersByCredential(Credential credential) {
		return orderRespository.findByCredential(credential);
	}

	public List<Order> getOrdersByCredentialAndStatus(Credential credential, String status) {
		return orderRespository.findByCredentialAndStatus(credential, status);
	}

	public Order updateOrderByOrderId(Order order) {
		handleException(checkIfOrderIdExists(order));

		order.setOrderId(this.getOrderByOrderId(order.getOrderId()).getOrderId());
		return orderRespository.save(order);
	}

	public List<Order> updateOrdersStatusByCredential(Order frequency) {
		handleException(checkIfOrderCredentialExists(frequency));

		for (Order eachOrder : this.getOrdersByCredential(frequency.getCredential())) {
			eachOrder.setStatus(frequency.getStatus());
			orderRespository.save(frequency);
		}
		return this.getOrdersByCredential(frequency.getCredential());
	}

	public List<Order> updateOrdersStatusByCredentialAndStatus(Order order) {
		handleException(checkIfOrderCredentialAndStatusExists(order));

		for (Order eachOrder : this.getOrdersByCredentialAndStatus(order.getCredential(), order.getStatus())) {
			eachOrder.setStatus(order.getStatus());
			orderRespository.save(order);
		}
		return this.getOrdersByCredentialAndStatus(order.getCredential(), order.getStatus());
	}

	public void deleteOrdersByCredential(Order order) {
		for (Order eachOrder : this.getOrdersByCredential(order.getCredential())) {
			orderRespository.delete(eachOrder);
		}
		return;
	}

	public void deleteOrdersByCredentialAndStatus(Order order) {
		for (Order eachOrder : this.getOrdersByCredentialAndStatus(order.getCredential(), order.getStatus())) {
			orderRespository.delete(eachOrder);
		}
		return;
	}

	public void deleteOrderByOrderId(Order order) {
		handleException(checkIfOrderIdExists(order));
		orderRespository.delete(order);
		return;
	}
}
