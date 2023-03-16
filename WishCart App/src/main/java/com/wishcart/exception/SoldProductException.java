package com.wishcart.exception;

public class SoldProductException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SoldProductException() {
	}

	public SoldProductException(String message) {
		super(message);
	}

}
