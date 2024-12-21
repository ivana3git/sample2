package com.useproduct.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UseConfiguration {
@Bean
public RestTemplate rest() {
	return new RestTemplate();
}
}
