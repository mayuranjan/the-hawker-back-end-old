package co.in.hawker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Order;
import co.in.hawker.entities.OrderItem;
import co.in.hawker.entities.Product;

/**
 * @author Siva
 * 
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
	
	OrderItem findByOrderItemId(Long orderItemId);

	List<OrderItem> findByOrder(Order order);

	List<OrderItem> findByProduct(Product product);

	List<OrderItem> findByStatus(String status);

	List<OrderItem> findByOrderAndStatus(Order order, String status);

	OrderItem findByOrderAndProduct(Order order, Product product);
	
}