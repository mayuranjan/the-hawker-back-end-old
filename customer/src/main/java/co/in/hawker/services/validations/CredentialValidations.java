package co.in.hawker.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import co.in.hawker.entities.Credential;
import co.in.hawker.repositories.CredentialRepository;
import co.in.hawker.util.Validation;

public abstract class CredentialValidations {
	@Autowired
	private CredentialRepository credentialRespository;

	protected Validation validation;

	// check whose opposite is wrong
	protected Validation checkIfCredentialIdDoesntExists(Credential credential) {
		Boolean isValid = credentialRespository.findByCredentialId(credential.getCredentialId()) == null;
		String errorMessage = "Credential ID \"" + credential.getCredentialId()
				+ "\" already exist, can't add/update Credential with same Credential ID.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfMasterCredentialIdDoesntExists(Credential credential) {
		Boolean isValid = credentialRespository.findByMasterCredentialId(credential.getCredentialId()) == null;
		String errorMessage = "Master Credential ID \"" + credential.getCredentialId()
				+ "\" already exist, can't add/update Credential with same Master Credential ID.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfUsernameDoesntExists(Credential credential) {
		Boolean isValid = credentialRespository.findByUsername(credential.getUsername()) == null;
		String errorMessage = "Username \"" + credential.getUsername()
				+ "\" already exist, can't add/update Credential with same Credential Username.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfMobileNumberDoesntExists(Credential credential) {
		Boolean isValid = credentialRespository.findByMobileNumber(credential.getMobileNumber()) == null;
		String errorMessage = "Mobile Number \"" + credential.getMobileNumber()
				+ "\" already exist, can't add/update Credential with same Mobile Number.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfEmailIdDoesntExists(Credential credential) {
		Boolean isValid = credentialRespository.findByEmailId(credential.getEmailId()) == null;
		String errorMessage = "Email ID \"" + credential.getEmailId()
				+ "\" already exist, can't add/update Credential with same Email ID.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfCredentialIdExists(Credential credential) {
		Boolean isValid = credentialRespository.findByCredentialId(credential.getCredentialId()) == null;
		String errorMessage = "Credential ID \"" + credential.getCredentialId()
				+ "\" doesn't exist, can't get/delete Credential.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfMasterCredentialIdExists(Credential credential) {
		Boolean isValid = credentialRespository.findByCredentialId(credential.getCredentialId()) == null;
		String errorMessage = "Master Credential ID \"" + credential.getCredentialId()
				+ "\" doesn't exist, can't get/delete Credential.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfUsernameExists(Credential credential) {
		Boolean isValid = credentialRespository.findByUsername(credential.getUsername()) == null;
		String errorMessage = "Username \"" + credential.getUsername() + "\" doesn't exist, can't get/delete Credential.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfMobileNumberExists(Credential credential) {
		Boolean isValid = credentialRespository.findByMobileNumber(credential.getMobileNumber()) == null;
		String errorMessage = "Mobile Number \"" + credential.getMobileNumber()
				+ "\" doesn't exist, can't get/delete Credential.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfEmailIdExists(Credential credential) {
		Boolean isValid = credentialRespository.findByEmailId(credential.getEmailId()) == null;
		String errorMessage = "Email ID \"" + credential.getEmailId() + "\" doesn't exist, can't get/delete Credential.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPasswordIsNotNull(Credential credential) {
		Boolean isValid = credential.getPassword() != null ? true : false;
		String errorMessage = "Password can't be empty.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected void handleException(Validation validation) throws RuntimeException {
		if (!validation.getIsValid()) {
			throw new RuntimeException(validation.getErrorMessage());
		}
	}
}