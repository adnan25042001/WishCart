package com.wishcart.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExceptionDetails {
	
	private LocalDateTime timeStamp;
	private String message;
	private String details;

}
