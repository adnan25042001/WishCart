package com.wishcart.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionMessage> MyInvalidDataExpHandler(MethodArgumentNotValidException ma, WebRequest wr) {

		ExceptionMessage me = new ExceptionMessage();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(ma.getBindingResult().getFieldError().getDefaultMessage());
		me.setDetails(wr.getDescription(false));

		return new ResponseEntity<ExceptionMessage>(me, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionMessage> myAnyExpHandler(Exception e, WebRequest wr) {

		ExceptionMessage me = new ExceptionMessage();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(e.getMessage());
		me.setDetails(wr.getDescription(false));

		return new ResponseEntity<ExceptionMessage>(me, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ExceptionMessage> myNoHandler(NoHandlerFoundException he, WebRequest wr) {

		ExceptionMessage me = new ExceptionMessage();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(he.getMessage());
		me.setDetails(wr.getDescription(false));

		return new ResponseEntity<ExceptionMessage>(me, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ExceptionMessage> myNoHandler(UserException e, WebRequest wr) {

		ExceptionMessage me = new ExceptionMessage();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(e.getMessage());
		me.setDetails(wr.getDescription(false));

		return new ResponseEntity<ExceptionMessage>(me, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(TokenException.class)
	public ResponseEntity<ExceptionMessage> myNoHandler(TokenException e, WebRequest wr) {

		ExceptionMessage me = new ExceptionMessage();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(e.getMessage());
		me.setDetails(wr.getDescription(false));

		return new ResponseEntity<ExceptionMessage>(me, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ExceptionMessage> productExceptionhandler(ProductException ex, WebRequest req) {

		ExceptionMessage err = new ExceptionMessage();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionMessage>(err, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(CartException.class)
	public ResponseEntity<ExceptionMessage> CartExceptionhandler(CartException ex, WebRequest req) {

		ExceptionMessage err = new ExceptionMessage();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionMessage>(err, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<ExceptionMessage> CategoryExceptionhandler(CategoryException ex, WebRequest req) {

		ExceptionMessage err = new ExceptionMessage();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionMessage>(err, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(WishlistException.class)
	public ResponseEntity<ExceptionMessage> WishistExceptionhandler(WishlistException ex, WebRequest req) {

		ExceptionMessage err = new ExceptionMessage();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionMessage>(err, HttpStatus.BAD_GATEWAY);
	}

}
