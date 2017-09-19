package co.in.hawker.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import co.in.hawker.entities.Profile;
import co.in.hawker.repositories.ProfileRepository;
import co.in.hawker.util.Validation;

public abstract class ProfileValidations {
	@Autowired
	private ProfileRepository profileRespository;

	protected Validation validation;

	protected Validation checkIfProfileIdDoesntExists(Profile profile) {
		Boolean isValid = profileRespository.findByProfileId(profile.getProfileId()) != null;
		String errorMessage = "Profile ID \"" + profile.getProfileId()
				+ "\" already exist, can't add/update Profile with same Profile ID.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfProfileIdExists(Profile profile) {
		Boolean isValid = profileRespository.findByProfileId(profile.getProfileId()) == null;
		String errorMessage = "Profile ID \"" + profile.getProfileId() + "\" doesn't exist, can't get/delete Profile.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfEmailIdExists(Profile profile) {
		Boolean isValid = profileRespository.findByEmailId(profile.getEmailId()) == null;
		String errorMessage = "Email Id \"" + profile.getEmailId() + "\" doesn't exist, can't get/delete Profile.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfMobileNumberExists(Profile profile) {
		Boolean isValid = profileRespository.findByMobileNumber(profile.getMobileNumber()) == null;
		String errorMessage = "Mobile Nummber \"" + profile.getMobileNumber()
				+ "\" doesn't exist, can't get/delete Profile.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfFirstNameExists(Profile profile) {
		Boolean isValid = profileRespository.findByFirstName(profile.getFirstName()) == null;
		String errorMessage = "First Name \"" + profile.getFirstName() + "\" doesn't exist, can't get/delete Profile.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfLastNameExists(Profile profile) {
		Boolean isValid = profileRespository.findByLastName(profile.getLastName()) == null;
		String errorMessage = "Last Name \"" + profile.getLastName() + "\" doesn't exist, can't get/delete Profile.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfFirstNameAndLastNameExists(Profile profile) {
		Boolean isValid = profileRespository.findByFirstNameAndLastName(profile.getFirstName(),
				profile.getLastName()) == null;
		String errorMessage = "First Name \"" + profile.getFirstName() + " Last Name \"" + profile.getLastName()
				+ "\" doesn't exist, can't get/delete Profile.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfDateOfBirthExists(Profile profile) {
		Boolean isValid = profileRespository.findByDateOfBirth(profile.getDateOfBirth()) == null;
		String errorMessage = "Date of Birth \"" + profile.getDateOfBirth()
				+ "\" doesn't exist, can't get/delete Profile.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfSexExists(Profile profile) {
		Boolean isValid = profileRespository.findBySex(profile.getSex()) == null;
		String errorMessage = "Sex \"" + profile.getSex() + "\" doesn't exist, can't get/delete Profile.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfCredentialExists(Profile profile) {
		Boolean isValid = profileRespository.findByCredential(profile.getCredential()) == null;
		String errorMessage = "Credential Id \"" + profile.getCredential().getCredentialId()
				+ "\" doesn't exist, can't get/delete Profile.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfStatusExists(Profile profile) {
		Boolean isValid = profileRespository.findByStatus(profile.getStatus()) == null;
		String errorMessage = "Status \"" + profile.getStatus() + "\" doesn't exist, can't get/delete Profile.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected void handleException(Validation validation) throws RuntimeException {
		if (!validation.getIsValid()) {
			throw new RuntimeException(validation.getErrorMessage());
		}
	}
}