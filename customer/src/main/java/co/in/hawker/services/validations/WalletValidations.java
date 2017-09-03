package co.in.hawker.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import co.in.hawker.repositories.WalletRepository;
import co.in.hawker.util.Validation;

public abstract class WalletValidations {
	@Autowired
	private WalletRepository walletRespository;

	protected Validation validation;

	
	protected void handleException(Validation validation) throws RuntimeException {
		if (!validation.getIsValid()) {
			throw new RuntimeException(validation.getErrorMessage());
		}
	}
}