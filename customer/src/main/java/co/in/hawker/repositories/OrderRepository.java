package co.in.hawker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Credential;
import co.in.hawker.entities.Order;

/**
 * @author Siva
 * 
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	Order findByOrderId(Long orderId);

	List<Order> findByCredential(Credential credential);

	List<Order> findByStatus(String status);

	List<Order> findByCredentialAndStatus(Credential credential, String status);
	
}