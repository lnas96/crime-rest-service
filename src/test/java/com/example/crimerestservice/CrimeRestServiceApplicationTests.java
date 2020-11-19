package com.example.crimerestservice;

import com.example.crimerestservice.controller.CrimeController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class CrimeRestServiceApplicationTests {

	@Autowired
	private CrimeController crimeController;

	@Autowired
	private RestTemplate restTemplate;

	@Test
	void controllerContextLoads() throws Exception {
		assertThat(crimeController).isNotNull();
	}

	@Test
	void restTemplateContextLoads() throws Exception{
		assertThat(restTemplate).isNotNull();
	}
}
