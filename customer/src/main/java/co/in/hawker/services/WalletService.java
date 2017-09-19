package co.in.hawker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public Wallet getPremiseByPremiseId(Long walletId) {
		Wallet wallet = new Wallet();
		wallet.setWalletId(walletId);

		handleException(checkIfWalletIdExists(wallet));
		return walletRespository.findByWalletId(walletId);
	}
}
