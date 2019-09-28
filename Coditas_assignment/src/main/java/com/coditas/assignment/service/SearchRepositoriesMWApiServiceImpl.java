package com.coditas.assignment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.coditas.assignment.exception.ApplicationException;
import com.coditas.assignment.exception.ErrorCode;
import com.coditas.assignment.properties.MiddlewareServiceProperties;
import com.coditas.assignment.util.APIHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SearchRepositoriesMWApiServiceImpl implements MiddlewareApiService {

	public static final Logger LOGGER = LoggerFactory.getLogger(SearchRepositoriesMWApiServiceImpl.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	MiddlewareServiceProperties mwProperties;

	/**
	 * This method is used to fetch all the public repos of github and gitlab
	 */
	@Override
	public Map<String, List<Map<String, Object>>> getAllRepos() throws JsonProcessingException {

		Map<String, List<Map<String, Object>>> response = new HashMap<>();
		
		// userName will be null or empty in this case
		String gitHubUrl = APIHelper.generateGitHubUrl(mwProperties.getGithubUrl(), null);	
		LOGGER.info("fetching all the public repos with URL -> {}", gitHubUrl);
		// userName will be null or empty in this case
		getGithubProjects(null, gitHubUrl, response);												
		// need to insert code here for fetching all the projects from gitlab api
		// getGitLabProjects(null, gitLabUrl, response) 
		return response;
	}

	/**
	 * This method is used to fetch all the public repos of a specific user
	 */
	@Override
	public Map<String, List<Map<String, Object>>> getAllReposForUser(String userName)
			throws JsonProcessingException, ApplicationException {

		// userName is a mandatory in this case so if empty validation error
		if (StringUtils.isEmpty(userName)) {
			throw new ApplicationException(ErrorCode.VALIDATION_ERROR, "No username in the request");
		}
		Map<String, List<Map<String, Object>>> response = new HashMap<>();
		String gitHubUrl = APIHelper.generateGitHubUrl(mwProperties.getGithubUrl(), userName);
		LOGGER.info("fetching all the reops for user -> {} with URL -> {}", userName, gitHubUrl);
		getGithubProjects(userName, gitHubUrl, response);
		// getGitLabProjects(userName, gitLabUrl, response) 
		return response;
	}

	/**
	 * This method is used to fetch the list of all the repos from GITHUB. Made this
	 * method generic, since url is different but the response type is same for
	 * fetching user specific public repos and all public repos.
	 * 
	 * @param userName
	 * @param response
	 * @return
	 * @throws JsonProcessingException
	 */
	private Map<String, List<Map<String, Object>>> getGithubProjects(String userName, String gitHubUrl,
			Map<String, List<Map<String, Object>>> response) throws JsonProcessingException {

		Object responseObject = restTemplate.getForObject(gitHubUrl, Object.class);
		List<Map<String, Object>> listOfAllGithubProjects = (List<Map<String, Object>>) responseObject;
		response.put("gitHub", listOfAllGithubProjects);
		/*
		 * this response map's key will be either github/gitlab and its value will be
		 * the list of projects(which is a map)
		 */
		LOGGER.info("Response From the github API -> {}",
				new ObjectMapper().writeValueAsString(listOfAllGithubProjects));
		return response;
	}
}
