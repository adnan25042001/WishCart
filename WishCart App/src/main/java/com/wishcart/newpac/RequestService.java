package com.wishcart.newpac;

import javax.servlet.http.HttpServletRequest;

public interface RequestService {
	
	public String getClientIPAddress(HttpServletRequest req);
	
}
