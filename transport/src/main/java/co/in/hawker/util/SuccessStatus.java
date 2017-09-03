package co.in.hawker.util;

public class SuccessStatus extends Status {

	public SuccessStatus(String message, Object object) {
		super(1, "Success", message, object);
	}

}