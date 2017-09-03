package co.in.hawker.util;

public class Validation {

	private Boolean isValid;
	private String errorMessage;

	public Validation() {
	}

	public Validation(Boolean isValid, String errorMessage) {
		this.isValid = isValid;
		this.errorMessage = errorMessage;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}