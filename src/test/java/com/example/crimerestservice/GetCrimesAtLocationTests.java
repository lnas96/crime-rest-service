package com.example.crimerestservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GetCrimesAtLocationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void validRequest() throws Exception {
		String expectedBody = "[" +
				"{" +
				"\"category\":\"anti-social-behaviour\"," +
				"\"location\":{" +
				"\"longitude\":\"-2.453281\"," +
				"\"latitude\":\"52.671441\"," +
				"\"postcode\":null" +
				"}," +
				"\"context\":\"\"," +
				"\"outcome_status\":null," +
				"\"month\":\"2020-02\"," +
				"\"postcode\":null" +
				"}," +
				"{" +
				"\"category\":\"possession-of-weapons\"," +
				"\"location\":{" +
				"\"longitude\":\"-2.453281\"," +
				"\"latitude\":\"52.671441\"," +
				"\"postcode\":null" +
				"}," +
				"\"context\":\"\"," +
				"\"outcome_status\":{" +
				"\"category\":\"Unable to prosecute suspect\"," +
				"\"date\":\"2020-04\"" +
				"}," +
				"\"month\":\"2020-02\"," +
				"\"postcode\":null" +
				"}" +
				"]";

		String url = "/crimes?postcode=tf32hs&date=2020-02";

		this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk()).
				andExpect(content().string(expectedBody));
	}

	@Test
	void missingPostcode() throws Exception {
		String url = "/crimes?postcode=&date=2020-02";
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void missingDate() throws Exception {
		String url = "/crimes?postcode=tf32hs&date=";
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void invalidPostcode() throws Exception {
		String url = "/crimes?postcode=tf1111&date=2020-02";
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void invalidDate() throws Exception {
		String url = "/crimes?postcode=tf32hs&date=20-January";
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void noData() throws Exception {
		String url = "/crimes?postcode=tf32hs&date=1899-01";
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isNotFound());
	}
}
