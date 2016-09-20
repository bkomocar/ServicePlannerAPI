package hr.tvz.serviceplanner.validation;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerValidationHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ArrayList<MessageDTO> processValidationError(MethodArgumentNotValidException ex) {
		ArrayList<MessageDTO> messages = new ArrayList<>();
		BindingResult result = ex.getBindingResult();
		if (result.getFieldErrors() != null && !result.getFieldErrors().isEmpty()) {
			for(FieldError error : result.getFieldErrors()){
				MessageDTO msg = processFieldError(error);
				if(msg!=null){
					messages.add(msg);
				}
			}
		}
		return messages;
	}

	private MessageDTO processFieldError(FieldError error) {
		MessageDTO message = null;
		if (error != null) {
			String msg = error.getDefaultMessage();
			String field = error.getField();
			message = new MessageDTO(msg, field);
			if (error.getCodes() != null && error.getCodes().length >= 1) {
				String code = error.getCodes()[error.getCodes().length - 1];
				message.setCode(code);
			}
		}
		return message;
	}
}
