package com.coditas.assignment.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.coditas.assignment.properties.MiddlewareServiceProperties;
import com.coditas.assignment.util.TestUtility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith(MockitoJUnitRunner.class)
public class SearchReposMwApiServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchReposMwApiServiceTest.class);

	@Mock
	private static RestTemplate restTemplate;

	@Mock
	private static MiddlewareServiceProperties mwProperties;

	@InjectMocks
	private static SearchRepositoriesMWApiServiceImpl searchReposService;

	Map<String, List<Map<String, Object>>> responseObjectForUser;

	Object responseFromRestTemplate;

	@Before
	public void setup() throws JsonParseException, JsonMappingException, IOException {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllReposForUser() throws Exception {

		responseObjectForUser = TestUtility.getDefaultResponseForFetchRepos();
		String userName = "sohammodhe";
		LOGGER.info("Testing middleware service to fetch all the repos for user -> {}", userName);
		Mockito.when(searchReposService.getAllReposForUser(userName)).thenReturn(responseObjectForUser);
		Mockito.when(restTemplate.getForObject("url", Object.class)).thenReturn(responseFromRestTemplate);
		Mockito.when(mwProperties.getGithubUrl()).thenReturn("some-test-url/");
		assertEquals(responseObjectForUser, searchReposService.getAllReposForUser(userName));
	}
}
