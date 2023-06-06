package com.wishcart.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionMessage {

    private boolean success = false;
    private LocalDateTime timeStamp;
    private String message;
    private String details;

}
