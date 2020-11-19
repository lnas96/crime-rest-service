package com.example.crimerestservice;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GetCrimeCategoriesTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void validRequest() throws Exception {
		String expectedBody = "{" +
				"\"categories\":[" +
				"\"All crime\"," +
				"\"Anti-social behaviour\"," +
				"\"Bicycle theft\"," +
				"\"Burglary\"," +
				"\"Criminal damage and arson\"," +
				"\"Drugs\"," +
				"\"Other theft\"," +
				"\"Possession of weapons\"," +
				"\"Public order\"," +
				"\"Robbery\"," +
				"\"Shoplifting\"," +
				"\"Theft from the person\"," +
				"\"Vehicle crime\"," +
				"\"Violence and sexual offences\"," +
				"\"Other crime\"" +
				"]" +
				"}";

		this.mockMvc.perform(get("/crime/categories")).andDo(print()).andExpect(status().isOk()).
				andExpect(content().string(expectedBody));
	}

	@Test
	void noData() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isNotFound());
	}
}
