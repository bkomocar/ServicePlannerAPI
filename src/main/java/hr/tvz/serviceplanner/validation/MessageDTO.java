package hr.tvz.serviceplanner.validation;

public class MessageDTO {
	private String message;
	private String field;
	private String code;

	public MessageDTO() {
		super();
	}

	public MessageDTO(String message, String field, String code) {
		super();
		this.message = message;
		this.field = field;
		this.code = code;
	}

	public MessageDTO(String message, String field) {
		super();
		this.message = message;
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
