package co.in.hawker.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import co.in.hawker.entities.Brand;
import co.in.hawker.repositories.BrandRepository;
import co.in.hawker.util.Validation;

public abstract class BrandValidations {
	@Autowired
	private BrandRepository brandRespository;

	protected Validation validation;

	// check whose opposite is wrong
	protected Validation checkIfBrandCodeDoesntExists(Brand brand) {
		Boolean isValid = brandRespository.findByBrandCode(brand.getBrandCode()) == null;
		String errorMessage = "Brand Code \"" + brand.getBrandCode()
				+ "\" already exist, can't add/update Brand with same Brand Code.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfBrandCodeExists(Brand brand) {
		Boolean isValid = brandRespository.findByBrandCode(brand.getBrandCode()) == null;
		String errorMessage = "Brand Code \"" + brand.getBrandCode() + "\" doesn't exist, can't delete Brand.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfBrandNameDoesntExists(Brand brand) {
		Boolean isValid = brandRespository.findByBrandName(brand.getBrandName()) == null;
		String errorMessage = "Brand Name \"" + brand.getBrandName()
				+ "\" already exist, can't add/update Brand with same Brand Name.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfBrandNameExists(Brand brand) {
		Boolean isValid = brandRespository.findByBrandName(brand.getBrandName()) == null;
		String errorMessage = "Brand Name \"" + brand.getBrandName() + "\" doesn't exist, can't delete Brand.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfBrandIdDoesntExists(Brand brand) {
		Boolean isValid = brandRespository.findByBrandId(brand.getBrandId()) == null;
		String errorMessage = "Brand Id \"" + brand.getBrandId()
				+ "\" already exist, can't add/update Brand with same Brand Id.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfBrandIdExists(Brand brand) {
		Boolean isValid = brandRespository.findByBrandId(brand.getBrandId()) == null;
		String errorMessage = "Brand Id \"" + brand.getBrandId() + "\" doesn't exist, can't delete Brand.";
		validation = new Validation(!isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfBrandNameIsNotNull(Brand brand) {
		Boolean isValid = brand.getBrandName() != null ? true : false;
		String errorMessage = "Brand Name can't be empty.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected Validation checkIfBrandCodeIsNotNull(Brand brand) {
		Boolean isValid = brand.getBrandCode() != null ? true : false;
		String errorMessage = "Brand Code can't be empty.";
		validation = new Validation(isValid, errorMessage);
		return validation;
	}

	protected void handleException(Validation validation) throws RuntimeException {
		if (!validation.getIsValid()) {
			throw new RuntimeException(validation.getErrorMessage());
		}
	}
}