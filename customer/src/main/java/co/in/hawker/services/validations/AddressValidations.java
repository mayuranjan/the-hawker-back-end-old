package co.in.hawker.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import co.in.hawker.entities.Address;
import co.in.hawker.repositories.AddressRepository;
import co.in.hawker.util.Validation;

public abstract class AddressValidations {
	@Autowired
	private AddressRepository addressRespository;

	protected Validation validation;

	// check whose opposite is wrong
	protected Validation checkIfAddressIdDoesntExists(Address address) {
		Boolean isValid = addressRespository.findByAddressId(address.getAddressId()) == null;
		String errorMessage = "Address ID \"" + address.getAddressId()
				+ "\" already exist, can't add/update Address with same Address ID.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfCredentialDoesntExists(Address address) {
		Boolean isValid = addressRespository.findByCredential(address.getCredential()) == null;
		String errorMessage = "";
		if (address.getCredential().getUsername() != null) {
			errorMessage = "Credential with username \"" + address.getCredential().getUsername()
					+ "\" already exist, can't add/update Address with same Credential.";
		} else {
			if (address.getCredential().getMobileNumber() != 0) {
				errorMessage = "Credential with mobile number \"" + address.getCredential().getMobileNumber()
						+ "\" already exist, can't add/update Address with same Credential.";
			} else {
				if (address.getCredential().getEmailId() != null) {
					errorMessage = "Credential with email id \"" + address.getCredential().getEmailId()
							+ "\" already exist, can't add/update Address with same Credential.";
				}
			}
		}

		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfAddressIdExists(Address address) {
		Boolean isValid = addressRespository.findByAddressId(address.getAddressId()) == null;
		String errorMessage = "Address ID \"" + address.getAddressId() + "\" doesn't exist, can't delete Address.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfCredentialExists(Address address) {
		Boolean isValid = addressRespository.findByCredential(address.getCredential()) == null;
		String errorMessage = "";
		if (address.getCredential().getUsername() != null) {
			errorMessage = "Credential with username \"" + address.getCredential().getUsername()
					+ "\" doesn't exist, can't delete Address.";
		} else {
			if (address.getCredential().getMobileNumber() != 0) {
				errorMessage = "Credential with mobile number \"" + address.getCredential().getMobileNumber()
						+ "\" doesn't exist, can't delete Address.";
			} else {
				if (address.getCredential().getEmailId() != null) {
					errorMessage = "Credential with email id \"" + address.getCredential().getEmailId()
							+ "\" doesn't exist, can't delete Address.";
				}
			}
		}
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfCredentialIsNotNull(Address address) {
		Boolean isValid = address.getCredential() != null ? true : false;
		String errorMessage = "Credential can't be empty.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected void handleException(Validation validation) throws RuntimeException {
		if (!validation.getIsValid()) {
			throw new RuntimeException(validation.getErrorMessage());
		}
	}
}