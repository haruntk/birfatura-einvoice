package com.htk.e_invoice_integration.integration.birfatura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
	
	@Bean
	public RestClient getRestClient() {
		return RestClient.create();
	}

}
