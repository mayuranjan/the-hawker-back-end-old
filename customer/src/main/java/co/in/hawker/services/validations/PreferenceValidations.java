package co.in.hawker.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import co.in.hawker.entities.Preference;
import co.in.hawker.repositories.PreferenceRepository;
import co.in.hawker.repositories.ProductRepository;
import co.in.hawker.util.Validation;

public abstract class PreferenceValidations {
	@Autowired
	private PreferenceRepository preferenceRespository;

	@Autowired
	private ProductRepository productRepository;

	protected Validation validation;

	// check whose opposite is wrong
	protected Validation checkIfPreferenceIdDoesntExists(Preference preference) {
		Boolean isValid = preferenceRespository.findByPreferenceId(preference.getPreferenceId()) == null;
		String errorMessage = "Preference ID \"" + preference.getPreferenceId()
				+ "\" already exist, can't add/update Preference with same Preference ID.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPreferenceIdExists(Preference preference) {
		Boolean isValid = preferenceRespository.findByPreferenceId(preference.getPreferenceId()) == null;
		String errorMessage = "Preference ID \"" + preference.getPreferenceId()
				+ "\" doesn't exist, can't delete Preference.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPreferenceStatusExists(Preference preference) {
		Boolean isValid = preferenceRespository.findByStatus(preference.getStatus()) == null;
		String errorMessage = "Status \"" + preference.getStatus() + "\" doesn't exist, can't delete Preference.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPreferenceCredentialExists(Preference preference) {
		Boolean isValid = preferenceRespository.findByCredential(preference.getCredential()) == null;
		String errorMessage = "Preference with Credential \"" + preference.getCredential()
				+ "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPreferenceLanguageExists(Preference preference) {
		Boolean isValid = preferenceRespository.findByLanguage(preference.getLanguage()) == null;
		String errorMessage = "Preference with Language \"" + preference.getLanguage()
				+ "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPreferenceProductMilkExists(Preference preference) {
		Boolean isValid = preferenceRespository.findByMilk(preference.getMilk()) == null;
		String errorMessage = "Preference with Milk \"" + preference.getMilk()
				+ "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfProductIsMilk(Preference preference) {
		Boolean isValid = productRepository.findByType(preference.getMilk().getType()) == null;
		String errorMessage = "Preference with Milk \"" + preference.getMilk()
				+ "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPreferenceProductWaterExists(Preference preference) {
		Boolean isValid = preferenceRespository.findByWater(preference.getWater()) == null;
		String errorMessage = "Preference with Water \"" + preference.getWater()
				+ "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPreferenceProductNewspaperExists(Preference preference) {
		Boolean isValid = preferenceRespository.findByNewspaper(preference.getNewspaper()) == null;
		String errorMessage = "Preference with Newspaper \"" + preference.getNewspaper()
				+ "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPreferencePreferredTimeSlotExists(Preference preference) {
		Boolean isValid = preferenceRespository.findByPreferredTimeSlot(preference.getPreferredTimeSlot()) == null;
		String errorMessage = "Preference with PreferredTimeSlot \"" + preference.getPreferredTimeSlot()
				+ "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPreferencePaymentModeExists(Preference preference) {
		Boolean isValid = preferenceRespository.findByPaymentMode(preference.getPaymentMode()) == null;
		String errorMessage = "Preference with PaymentMode \"" + preference.getPaymentMode()
				+ "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPreferenceChannelOfCommunicationExists(Preference preference) {
		Boolean isValid = preferenceRespository
				.findByChannelOfCommunication(preference.getChannelOfCommunication()) == null;
		String errorMessage = "Preference with Channel Of Communication \"" + preference.getChannelOfCommunication()
				+ "\" doesn't exist, can't delete Frequency.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPreferenceCredentialAndStatusExists(Preference preference) {
		Boolean isValid = preferenceRespository.findByCredentialAndStatus(preference.getCredential(),
				preference.getStatus()) == null;
		String errorMessage = "Preference with Credential \"" + preference.getCredential().getCredentialId()
				+ "\" and Status \"" + preference.getStatus() + "\" doesn't exist, can't delete Preference.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected void handleException(Validation validation) throws RuntimeException {
		if (!validation.getIsValid()) {
			throw new RuntimeException(validation.getErrorMessage());
		}
	}
}