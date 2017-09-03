package co.in.hawker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.repositories.ProfileRepository;
import co.in.hawker.services.validations.ProfileValidations;

@Service
@Transactional
public class ProfileService extends ProfileValidations {
	// public class CredentialService extends CredentialValidations {
	@Autowired
	private ProfileRepository profileRespository;

	
}
