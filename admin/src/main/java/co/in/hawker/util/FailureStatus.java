package co.in.hawker.util;

public class FailureStatus extends Status {

	private String exception;
	private String rootCause;
	private String reason;

	public FailureStatus(String message, String exception, String reason, Object object) {
		super(0, "Failure", message, object);

		this.exception = exception;
		this.reason = reason;
	}

	public FailureStatus(String message, String exception, String rootCause, String reason, Object object) {
		this(message, exception, reason, object);

		this.rootCause = rootCause;
	}

	public String getException() {
		return "Exception: " + exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getRootCause() {
		return "Root Cause: " + rootCause;
	}

	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}

	public String getReason() {
		return "Reason: " + reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}