package co.in.hawker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.entities.Credential;
import co.in.hawker.repositories.CredentialRepository;
import co.in.hawker.services.validations.CredentialValidations;

@Service
@Transactional
public class CredentialService extends CredentialValidations {
	// public class CredentialService extends CredentialValidations {
	@Autowired
	private CredentialRepository credentialRespository;

	public Credential addCredential(Credential credential) {

		// null check
		handleException(checkIfPasswordIsNotNull(credential));

		// duplicate check
		handleException(checkIfCredentialIdDoesntExists(credential));
		handleException(checkIfUsernameDoesntExists(credential));
		handleException(checkIfEmailIdDoesntExists(credential));
		handleException(checkIfMobileNumberDoesntExists(credential));

		return credentialRespository.save(credential);
	}

	public List<Credential> getAllCredentials() {
		return credentialRespository.findAll();
	}

	public Credential getCredentialByUsername(String username) {
		Credential credential = new Credential();
		credential.setUsername(username);

		handleException(checkIfUsernameExists(credential));
		return credentialRespository.findByUsername(username);
	}

	public Credential getCredentialByMobileNumber(int mobileNumber) {
		Credential credential = new Credential();
		credential.setMobileNumber(mobileNumber);

		handleException(checkIfMobileNumberExists(credential));
		return credentialRespository.findByMobileNumber(mobileNumber);
	}

	public Credential getCredentialByEmailId(String emailId) {
		Credential credential = new Credential();
		credential.setEmailId(emailId);

		handleException(checkIfEmailIdExists(credential));
		return credentialRespository.findByEmailId(emailId);
	}

	public Credential getCredentialByCredentialId(Long credentialId) {
		Credential credential = new Credential();
		credential.setCredentialId(credentialId);

		handleException(checkIfCredentialIdExists(credential));
		return credentialRespository.findByCredentialId(credentialId);
	}

	public List<Credential> getCredentialsByMasterCredentialId(Long masterCredentialId) {
		Credential credential = new Credential();
		credential.setCredentialId(masterCredentialId);

		handleException(checkIfMasterCredentialIdExists(credential));
		return credentialRespository.findByMasterCredentialId(masterCredentialId);
	}

	public Credential updateCredentialByUsername(Credential credential) {
		handleException(checkIfUsernameDoesntExists(credential));

		credential.setCredentialId(this.getCredentialByUsername(credential.getUsername()).getCredentialId());
		return credentialRespository.save(credential);
	}

	public Credential updateCredentialByMobileNumber(Credential credential) {
		handleException(checkIfMobileNumberDoesntExists(credential));

		credential.setCredentialId(this.getCredentialByMobileNumber(credential.getMobileNumber()).getCredentialId());
		return credentialRespository.save(credential);
	}

	public Credential updateCredentialByEmailId(Credential credential) {
		handleException(checkIfEmailIdDoesntExists(credential));

		credential.setCredentialId(this.getCredentialByEmailId(credential.getEmailId()).getCredentialId());
		return credentialRespository.save(credential);
	}

	public Credential updateCredentialByCredentialId(Credential credential) {
		handleException(checkIfCredentialIdDoesntExists(credential));

		credential.setCredentialId(this.getCredentialByCredentialId(credential.getCredentialId()).getCredentialId());
		return credentialRespository.save(credential);
	}

	// unimplemented
	// public Credential updateCredentialsByMasterCredentialId(Credential
	// credential) {
	// handleException(checkIfCredentialIdExists(credential));
	//
	// credential.setCredentialId(this.getCredentialsByMasterCredentialId(credential.getCredentialId()).getCredentialId());
	// return credentialRespository.save(credential);
	// }

	public void deleteCredentialByUsername(Credential credential) {
		handleException(checkIfUsernameExists(credential));

		credential.setCredentialId(this.getCredentialByUsername(credential.getUsername()).getCredentialId());
		credentialRespository.delete(credential);
		return;
	}

	public void deleteCredentialByMobileNumber(Credential credential) {
		handleException(checkIfMobileNumberExists(credential));

		credential.setCredentialId(this.getCredentialByMobileNumber(credential.getMobileNumber()).getCredentialId());
		credentialRespository.delete(credential);
		return;
	}

	public void deleteCredentialByEmailId(Credential credential) {
		handleException(checkIfEmailIdExists(credential));

		credential.setCredentialId(this.getCredentialByEmailId(credential.getEmailId()).getCredentialId());
		credentialRespository.delete(credential);
		return;
	}

	public void deleteCredentialByCredentialId(Credential credential) {
		handleException(checkIfCredentialIdExists(credential));
		credentialRespository.delete(credential);
		return;
	}

	public void deleteCredentialsByMasterCredentialId(Credential credential) {
		handleException(checkIfMasterCredentialIdExists(credential));
		for (Credential eachCredential : this.getCredentialsByMasterCredentialId(credential.getMasterCredentialId())) {
			credentialRespository.delete(eachCredential);
		}
		return;
	}

	public boolean validateUsernameAndPassword(Credential credential) {
		handleException(checkIfUsernameExists(credential));
		Credential dbCredential = credentialRespository.findByUsername(credential.getUsername());

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (credential.getUsername().equalsIgnoreCase(dbCredential.getUsername())
				&& encoder.matches(credential.getPassword(), dbCredential.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateMobileNumberAndPassword(Credential credential) {
		handleException(checkIfMobileNumberExists(credential));
		Credential dbCredential = credentialRespository.findByMobileNumber(credential.getMobileNumber());

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (credential.getMobileNumber() == dbCredential.getMobileNumber()
				&& encoder.matches(credential.getPassword(), dbCredential.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateEmailIdAndPassword(Credential credential) {
		handleException(checkIfEmailIdExists(credential));
		Credential dbCredential = credentialRespository.findByEmailId(credential.getEmailId());

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (credential.getEmailId().equalsIgnoreCase(dbCredential.getEmailId())
				&& encoder.matches(credential.getPassword(), dbCredential.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
}
