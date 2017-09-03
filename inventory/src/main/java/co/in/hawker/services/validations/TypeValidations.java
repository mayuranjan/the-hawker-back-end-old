package co.in.hawker.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import co.in.hawker.entities.Type;
import co.in.hawker.repositories.TypeRepository;
import co.in.hawker.util.Validation;

public abstract class TypeValidations {
	@Autowired
	private TypeRepository typeRespository;

	protected Validation validation;

	// check whose opposite is wrong
	protected Validation checkIfTypeCodeDoesntExists(Type type) {
		Boolean isValid = typeRespository.findByTypeCode(type.getTypeCode()) == null;
		String errorMessage = "Type Code \"" + type.getTypeCode()
				+ "\" already exist, can't add new Type with same Type Code.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfTypeIdDoesntExists(Type type) {
		Boolean isValid = typeRespository.findByTypeId(type.getTypeId()) == null;
		String errorMessage = "Type Id \"" + type.getTypeId()
				+ "\" already exist, can't add new Type with same Type Id.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfTypeNameIsNotNull(Type type) {
		Boolean isValid = type.getTypeName() != null ? true : false;
		String errorMessage = "Type Name can't be empty.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfTypeCodeIsNotNull(Type type) {
		Boolean isValid = type.getTypeCode() != null ? true : false;
		String errorMessage = "Type Code can't be empty.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected void handleException(Validation validation) throws RuntimeException {
		if (!validation.getIsValid()) {
			throw new RuntimeException(validation.getErrorMessage());
		}
	}
}