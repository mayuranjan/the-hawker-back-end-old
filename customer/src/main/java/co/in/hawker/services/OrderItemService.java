package co.in.hawker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.entities.Order;
import co.in.hawker.entities.OrderItem;
import co.in.hawker.entities.Product;
import co.in.hawker.repositories.OrderItemRepository;
import co.in.hawker.services.validations.OrderItemValidations;

@Service
@Transactional
public class OrderItemService extends OrderItemValidations {
	// public class CredentialService extends CredentialValidations {
	@Autowired
	private OrderItemRepository orderItemRespository;

	public OrderItem addOrderItem(OrderItem orderItem) {

		// duplicate check
		handleException(checkIfOrderItemIdDoesntExists(orderItem));
		return orderItemRespository.save(orderItem);
	}

	public List<OrderItem> getAllOrderItems() {
		return orderItemRespository.findAll();
	}

	public OrderItem getOrderItemByOrderItemId(Long orderItemId) {
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderItemId(orderItemId);

		handleException(checkIfOrderItemIdDoesntExists(orderItem));
		return orderItemRespository.findByOrderItemId(orderItemId);
	}

	public List<OrderItem> getOrderItemsByOrder(Order order) {
		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);

		handleException(checkIfOrderItemOrderExists(orderItem));
		return orderItemRespository.findByOrder(order);
	}

	public List<OrderItem> getOrderItemsByProduct(Product product) {
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);

		handleException(checkIfOrderItemProductExists(orderItem));
		return orderItemRespository.findByProduct(product);
	}

	public List<OrderItem> getOrderItemsByStatus(String status) {
		OrderItem orderItem = new OrderItem();
		orderItem.setStatus(status);

		handleException(checkIfOrderItemStatusExists(orderItem));
		return orderItemRespository.findByStatus(status);
	}

	public List<OrderItem> getOrderItemsByOrderAndStatus(Order order,String status) {
		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);
		orderItem.setStatus(status);

		handleException(checkIfOrderItemOrderAndStatusExists(orderItem));
		return orderItemRespository.findByOrderAndStatus(order, status);
	}

	public OrderItem getOrderItemByOrderAndProduct(Order order,Product product) {
		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);
		orderItem.setProduct(product);

		handleException(checkIfOrderItemOrderAndProductExists(orderItem));
		return orderItemRespository.findByOrderAndProduct(order, product);
	}
	
	public OrderItem updateOrderItemByOrderItemId(OrderItem orderItem) {
		handleException(checkIfOrderItemIdExists(orderItem));

		orderItem.setOrderItemId(this.getOrderItemByOrderItemId(orderItem.getOrderItemId()).getOrderItemId());
		return orderItemRespository.save(orderItem);
	}

	public List<OrderItem> updateOrderItemsStatusByOrder(OrderItem orderItem) {
		handleException(checkIfOrderItemOrderExists(orderItem));

		for (OrderItem eachOrderItem : this.getOrderItemsByOrder(orderItem.getOrder())) {
			eachOrderItem.setStatus(orderItem.getStatus());
			orderItemRespository.save(orderItem);
		}
		return this.getOrderItemsByOrder(orderItem.getOrder());
	}

	public List<OrderItem> updateOrderItemsStatusByProduct(OrderItem orderItem) {
		handleException(checkIfOrderItemProductExists(orderItem));

		for (OrderItem eachOrderItem : this.getOrderItemsByProduct(orderItem.getProduct())) {
			eachOrderItem.setStatus(orderItem.getStatus());
			orderItemRespository.save(orderItem);
		}
		return this.getOrderItemsByProduct(orderItem.getProduct());
	}

	public List<OrderItem> updateOrderItemsQuantityByProduct(OrderItem orderItem) {
		handleException(checkIfOrderItemProductExists(orderItem));

		for (OrderItem eachOrderItem : this.getOrderItemsByProduct(orderItem.getProduct())) {
			eachOrderItem.setQuantity(orderItem.getQuantity());
			orderItemRespository.save(orderItem);
		}
		return this.getOrderItemsByProduct(orderItem.getProduct());
	}

	public OrderItem updateOrderItemStatusByOrderAndProduct(OrderItem orderItem) {
		handleException(checkIfOrderItemOrderAndProductExists(orderItem));

		orderItem = this.getOrderItemByOrderAndProduct(orderItem.getOrder(), orderItem.getProduct());
		orderItem.setStatus(orderItem.getStatus());
		return orderItemRespository.save(orderItem); 
	}
	
	public void deleteOrderItemsByOrder(OrderItem orderItem) {
		for (OrderItem eachOrderItem : this.getOrderItemsByOrder(orderItem.getOrder())) {
			orderItemRespository.delete(eachOrderItem);
		}
		return;
	}
	
	public void deleteOrderItemsByProduct(OrderItem orderItem) {
		for (OrderItem eachOrderItem : this.getOrderItemsByProduct(orderItem.getProduct())) {
			orderItemRespository.delete(eachOrderItem);
		}
		return;
	}
	
	public void deleteOrderItemsByStatus(OrderItem orderItem) {
		for (OrderItem eachOrderItem : this.getOrderItemsByStatus(orderItem.getStatus())) {
			orderItemRespository.delete(eachOrderItem);
		}
		return;
	}
	
	public void deleteOrderItemsByOrderAndStatus(OrderItem orderItem) {
		for (OrderItem eachOrderItem : this.getOrderItemsByOrderAndStatus(orderItem.getOrder(), orderItem.getStatus())) {
			orderItemRespository.delete(eachOrderItem);
		}
		return;
	}

	public void deleteOrderItemByOrderAndProduct(OrderItem orderItem) {
		handleException(checkIfOrderItemOrderAndProductExists(orderItem));
		orderItem = this.getOrderItemByOrderAndProduct(orderItem.getOrder(), orderItem.getProduct());
		orderItemRespository.delete(orderItem);
		return;
	}

	public void deleteOrderItemByOrderItemId(OrderItem orderItem) {
		handleException(checkIfOrderItemIdExists(orderItem));
		orderItemRespository.delete(orderItem);
		return;
	}
	
}
