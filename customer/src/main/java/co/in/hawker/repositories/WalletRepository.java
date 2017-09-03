package co.in.hawker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Credential;
import co.in.hawker.entities.Order;
import co.in.hawker.entities.Wallet;

/**
 * @author Siva
 * 
 */
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

	Wallet findByCredential(Credential credential);
	
	Wallet findByOrder(Order order);
	
	List<Wallet> findByTransactionAmount(double transactionAmount);
	
	Wallet findByWalletId(Long walletId);
	
}