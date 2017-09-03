package co.in.hawker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.repositories.PreferenceRepository;
import co.in.hawker.services.validations.PreferenceValidations;

@Service
@Transactional
public class PreferenceService extends PreferenceValidations {
	// public class CredentialService extends CredentialValidations {
	@Autowired
	private PreferenceRepository preferenceRespository;

	
}
