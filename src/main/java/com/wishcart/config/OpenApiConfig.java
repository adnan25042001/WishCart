package com.wishcart.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				contact = @Contact(
						name = "Adnan Hussain",
						email = "adnan.hussain.136660@gmail.com",
						url = "https://adnan25042001.github.io/"
				),
				description = "OpenApi documentation for e-commerce application",
				title = "WishCart - Documentation",
				version = "1.0"
		),
		servers = {
				@Server(
						url = "http://localhost:8000",
						description = "Local ENV"
				),
				@Server(
						url = "http://localhost:8000",
						description = "PROD ENV"
				)
		}
)
@SecurityScheme(
		name = "bearerAuth",
		description = "First you have to create a new account or login in your account then you will get bearer token. Paste the bearer token in value",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}