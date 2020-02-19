package com.cts.rabobank.exceptionhandling;

import com.cts.rabobank.model.Response;
import com.cts.rabobank.utils.ProcessUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ExceptionHandlerControllerAdvice
 */

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	/**
	 * handleResourceNotFound
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody
    ResponseEntity<Response> handleResourceNotFound(final ResourceNotFoundException exception) {
		return ProcessUtils.processResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value());
	}

	/**
	 * handleRecordParseException
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(RecordParseException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody
    ResponseEntity<Response> handleRecordParseException(final Exception exception) {
		return ProcessUtils.processResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
	}

	/**
	 * handleException
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ResponseEntity<Response> handleException(final Exception exception) {
		return ProcessUtils.processResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

}
