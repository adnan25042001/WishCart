package com.wishcart.newpac;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RequestServiceImpl implements RequestService {

	private Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

	private final String LOCALHOST_IPV4 = "127.0.0.1";

	private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

	@Override
	public String getClientIPAddress(HttpServletRequest req) {

		String clientIPAddress = req.getHeader("X-Forwarded-For");

		if (!StringUtils.hasLength(clientIPAddress) || "unknown".equals(clientIPAddress)) {
			clientIPAddress = req.getHeader("Proxy-Client-IP");
		}

		if (!StringUtils.hasLength(clientIPAddress) || "unknown".equals(clientIPAddress)) {
			clientIPAddress = req.getHeader("WL-Proxy-Client-IP");
		}

		if (!StringUtils.hasLength(clientIPAddress) || "unknown".equals(clientIPAddress)) {
			clientIPAddress = req.getRemoteAddr();
			if (LOCALHOST_IPV4.equals(clientIPAddress) || LOCALHOST_IPV6.equals(clientIPAddress)) {
				try {
					InetAddress inetAddress = InetAddress.getLocalHost();
					clientIPAddress = inetAddress.getHostAddress();
				} catch (UnknownHostException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}

		if (StringUtils.hasLength(clientIPAddress) && clientIPAddress.length() > 15
				&& clientIPAddress.indexOf(",") > 0) {
			clientIPAddress = clientIPAddress.substring(0, clientIPAddress.indexOf(","));
		}

		System.out.println(clientIPAddress);

		return clientIPAddress;
	}

}
