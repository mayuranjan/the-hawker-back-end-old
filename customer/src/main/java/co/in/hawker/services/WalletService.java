package co.in.hawker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.repositories.WalletRepository;
import co.in.hawker.services.validations.WalletValidations;

@Service
@Transactional
public class WalletService extends WalletValidations {
	// public class CredentialService extends CredentialValidations {
	@Autowired
	private WalletRepository walletRespository;

	
}
