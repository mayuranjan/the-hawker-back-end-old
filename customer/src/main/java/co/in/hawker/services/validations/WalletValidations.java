package co.in.hawker.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import co.in.hawker.entities.Wallet;
import co.in.hawker.repositories.WalletRepository;
import co.in.hawker.util.Validation;

public abstract class WalletValidations {
	@Autowired
	private WalletRepository walletRespository;

	protected Validation validation;

	protected Validation checkIfWalletIdDoesntExists(Wallet wallet) {
		Boolean isValid = walletRespository.findByWalletId(wallet.getWalletId()) != null;
		String errorMessage = "Wallet Id \"" + wallet.getWalletId()
				+ "\" already exist, can't add/update Wallet with same Wallet ID.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfWalletIdExists(Wallet wallet) {
		Boolean isValid = walletRespository.findByWalletId(wallet.getWalletId()) == null;
		String errorMessage = "Wallet Id \"" + wallet.getWalletId() + "\" doesn't exist, can't get/delete Wallet.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfCredentialExists(Wallet wallet) {
		Boolean isValid = walletRespository.findByCredential(wallet.getCredential()) == null;
		String errorMessage = "Credential Id \"" + wallet.getCredential().getCredentialId()
				+ "\" doesn't exist, can't get/delete Credential.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfOrderExists(Wallet wallet) {
		Boolean isValid = walletRespository.findByOrder(wallet.getOrder()) == null;
		String errorMessage = "Order \"" + wallet.getOrder().getOrderId()
				+ "\" doesn't exist, can't get/delete Credential.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfTransactionAmountExists(Wallet wallet) {
		Boolean isValid = walletRespository.findByTransactionAmount(wallet.getTransactionAmount()) == null;
		String errorMessage = "Transaction Amount \"" + wallet.getTransactionAmount()
				+ "\" doesn't exist, can't get/delete Credential.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected void handleException(Validation validation) throws RuntimeException {
		if (!validation.getIsValid()) {
			throw new RuntimeException(validation.getErrorMessage());
		}
	}
}