package co.in.hawker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.entities.Credential;
import co.in.hawker.entities.Order;
import co.in.hawker.entities.Wallet;
import co.in.hawker.repositories.WalletRepository;
import co.in.hawker.services.validations.WalletValidations;

@Service
@Transactional
public class WalletService extends WalletValidations {
	// public class CredentialService extends CredentialValidations {
	@Autowired
	private WalletRepository walletRespository;

	public Wallet addWallet(Wallet wallet) {

		// null check

		// duplicate check
		handleException(checkIfWalletIdDoesntExists(wallet));
		return walletRespository.save(wallet);
	}

	public List<Wallet> getAllWallets() {
		return walletRespository.findAll();
	}

	public Wallet getWalletByWalletId(Long walletId) {
		Wallet wallet = new Wallet();
		wallet.setWalletId(walletId);

		handleException(checkIfWalletIdExists(wallet));
		return walletRespository.findByWalletId(walletId);
	}

	public Wallet getWalletByCredential(Credential credential) {
		Wallet wallet = new Wallet();
		wallet.setCredential(credential);

		handleException(checkIfCredentialExists(wallet));
		return walletRespository.findByCredential(credential);
	}

	public Wallet getWalletByOrder(Order order) {
		Wallet wallet = new Wallet();
		wallet.setOrder(order);

		handleException(checkIfOrderExists(wallet));
		return walletRespository.findByOrder(order);
	}

	public List<Wallet> getWalletsByTransactionAmount(double transactionAmount) {
		Wallet wallet = new Wallet();
		wallet.setTransactionAmount(transactionAmount);

		handleException(checkIfOrderExists(wallet));
		return walletRespository.findByTransactionAmount(transactionAmount);
	}

	public Wallet updateWalletByWalletId(Wallet wallet) {
		handleException(checkIfWalletIdDoesntExists(wallet));

		wallet.setWalletId(this.getWalletByWalletId(wallet.getWalletId()).getWalletId());
		return walletRespository.save(wallet);
	}

	public Wallet updateWalletByCredential(Wallet wallet) {

		wallet.setWalletId(this.getWalletByCredential(wallet.getCredential()).getWalletId());
		return walletRespository.save(wallet);
	}

	public Wallet updateWalletByOrder(Wallet wallet) {

		wallet.setWalletId(this.getWalletByOrder(wallet.getOrder()).getWalletId());
		return walletRespository.save(wallet);
	}

	public List<Wallet> updateWalletsByTransactionAmount(Wallet wallet) {
		for (Wallet eachWallet : this.getWalletsByTransactionAmount(wallet.getTransactionAmount())) {
			eachWallet.setTransactionAmount(wallet.getTransactionAmount());
			walletRespository.save(eachWallet);
		}

		return this.getWalletsByTransactionAmount(wallet.getTransactionAmount());
	}

	public void deleteWalletByWalletId(Wallet wallet) {
		handleException(checkIfWalletIdDoesntExists(wallet));

		wallet.setWalletId(this.getWalletByWalletId(wallet.getWalletId()).getWalletId());
		walletRespository.delete(wallet);
		return;
	}

	public void deleteWalletByCredential(Wallet wallet) {

		wallet.setWalletId(this.getWalletByCredential(wallet.getCredential()).getWalletId());
		walletRespository.delete(wallet);
		return;
	}

	public void deleteWalletByOrder(Wallet wallet) {

		wallet.setWalletId(this.getWalletByOrder(wallet.getOrder()).getWalletId());
		walletRespository.delete(wallet);
		return;
	}

	public void deleteWalletsByTransactionAmount(Wallet wallet) {
		for (Wallet eachWallet : this.getWalletsByTransactionAmount(wallet.getTransactionAmount())) {
			eachWallet.setTransactionAmount(wallet.getTransactionAmount());
			walletRespository.delete(eachWallet);
		}
		return;
	}
}
