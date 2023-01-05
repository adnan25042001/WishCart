package com.wishcart.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SessionDTO {

	private String authkey;
	private LocalDateTime sessionTime;

}
