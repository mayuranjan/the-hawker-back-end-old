package co.in.hawker.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import co.in.hawker.entities.Premise;
import co.in.hawker.repositories.PremiseRepository;
import co.in.hawker.util.Validation;

public abstract class PremiseValidations {
	@Autowired
	private PremiseRepository premiseRespository;

	protected Validation validation;

	// check whose opposite is wrong
	protected Validation checkIfPremiseIdDoesntExists(Premise premise) {
		Boolean isValid = premiseRespository.findByPremiseId(premise.getPremiseId()) != null;
		String errorMessage = "Premise ID \"" + premise.getPremiseId()
				+ "\" already exist, can't add/update Premise with same Premise ID.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPremiseIdExists(Premise premise) {
		Boolean isValid = premiseRespository.findByPremiseId(premise.getPremiseId()) == null;
		String errorMessage = "Premise ID \"" + premise.getPremiseId() + "\" doesn't exist, can't get/delete Premise.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfAreaExists(Premise premise) {
		Boolean isValid = premiseRespository.findByArea(premise.getArea()) == null;
		String errorMessage = "Area \"" + premise.getArea() + "\" doesn't exist, can't get/delete Premise.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfCountryExists(Premise premise) {
		Boolean isValid = premiseRespository.findByCountry(premise.getCountry()) == null;
		String errorMessage = "Country \"" + premise.getCountry() + "\" doesn't exist, can't get/delete Premise.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfNameExists(Premise premise) {
		Boolean isValid = premiseRespository.findByName(premise.getName()) == null;
		String errorMessage = "Name \"" + premise.getName() + "\" doesn't exist, can't get/delete Premise.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfPincodeExists(Premise premise) {
		Boolean isValid = premiseRespository.findByPincode(premise.getPincode()) == null;
		String errorMessage = "Pincode \"" + premise.getPincode() + "\" doesn't exist, can't get/delete Premise.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfStateExists(Premise premise) {
		Boolean isValid = premiseRespository.findByState(premise.getState()) == null;
		String errorMessage = "State \"" + premise.getState() + "\" doesn't exist, can't get/delete Premise.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfTypeExists(Premise premise) {
		Boolean isValid = premiseRespository.findByType(premise.getType()) == null;
		String errorMessage = "Type \"" + premise.getType() + "\" doesn't exist, can't get/delete Premise.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfNameAndBuilderExists(Premise premise) {
		Boolean isValid = premiseRespository.findByNameAndBuilder(premise.getName(), premise.getBuilder()) == null;
		String errorMessage = "Name \"" + premise.getName() + "\" and " + "Builder \"" + premise.getBuilder()
				+ "\" doesn't exist, can't get/delete Premise.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfBuilderExists(Premise premise) {
		Boolean isValid = premiseRespository.findByBuilder(premise.getBuilder()) == null;
		String errorMessage = "Builder \"" + premise.getBuilder() + "\" doesn't exist, can't get/delete Premise.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected void handleException(Validation validation) throws RuntimeException {
		if (!validation.getIsValid()) {
			throw new RuntimeException(validation.getErrorMessage());
		}
	}
}