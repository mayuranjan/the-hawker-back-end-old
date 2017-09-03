package co.in.hawker.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import co.in.hawker.entities.Frequency;
import co.in.hawker.repositories.FrequencyRepository;
import co.in.hawker.util.Validation;

public abstract class FrequencyValidations {
	@Autowired
	private FrequencyRepository frequencyRespository;

	protected Validation validation;

	// check whose opposite is wrong
	protected Validation checkIfFrequencyIdDoesntExists(Frequency frequency) {
		Boolean isValid = frequencyRespository.findByFrequencyId(frequency.getFrequencyId()) == null;
		String errorMessage = "Frequency ID \"" + frequency.getFrequencyId()
				+ "\" already exist, can't add/update Frequency with same Frequency ID.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfFrequencyIdExists(Frequency frequency) {
		Boolean isValid = frequencyRespository.findByFrequencyId(frequency.getFrequencyId()) == null;
		String errorMessage = "Frequency ID \"" + frequency.getFrequencyId()
				+ "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfFrequencyCredentialExists(Frequency frequency) {
		Boolean isValid = frequencyRespository.findByCredential(frequency.getCredential()) == null;
		String errorMessage = "Frequency with Credential \"" + frequency.getCredential()
				+ "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfFrequencyCredentialAndStatusExists(Frequency frequency) {
		Boolean isValid = frequencyRespository.findByCredentialAndActive(frequency.getCredential(),
				frequency.isActive()) == null;
		String errorMessage = "Frequency with Credential \"" + frequency.getCredential() + "\" and Status \""
				+ frequency.isActive() + "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected void handleException(Validation validation) throws RuntimeException {
		if (!validation.getIsValid()) {
			throw new RuntimeException(validation.getErrorMessage());
		}
	}
}