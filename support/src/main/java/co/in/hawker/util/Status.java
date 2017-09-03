package co.in.hawker.util;

public class Status {

	private Integer statusCode;
	private String message;
	private String status;
	private Object object;

	public Status() {
	}

	public Status(int statusCode, String status, String message, Object object) {
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.object = object;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}