package com.afkl.travel.exercise.config;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	public void givenAuthRequestOnLocationParams_shouldFailWith401() throws Exception {
		mvc.perform(get("/locations/country/US").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isUnauthorized());
	}

	@WithMockUser(value = "someuser")
	@Test
	public void givenAuthRequestOnLocations_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/locations").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
	}

	@WithMockUser(value = "someuser")
	@Test
	public void givenAuthRequestOnLocationParams_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/locations/country/NL").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is(4614)))
				.andExpect(jsonPath("$[0].code", is("NL"))).andExpect(jsonPath("$[0].type", is("country")));

	}

	@WithMockUser(value = "someuser")
	@Test
	public void givenAuthRequestOnLocationParams_shouldFailWith400() throws Exception {
		mvc.perform(get("/locations/  /NL").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest());
	}

	@WithMockUser(value = "someuser")
	@Test
	public void givenAuthRequestOnRestTemplate_shouldSuccessWith200() throws Exception {
		mvc.perform(get("/resttemplate").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
	}

}
