package com.coditas.assignment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coditas.assignment.exception.ApplicationException;
import com.coditas.assignment.service.MiddlewareApiService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/repos")
public class SearchRepositoriesApiController {

	public static final Logger LOGGER = LoggerFactory.getLogger(SearchRepositoriesApiController.class);

	@Autowired
	MiddlewareApiService middlewareApiService;

	@GetMapping(value = "/")
	public ResponseEntity<?> getAllReposInJsonFormat() throws JsonProcessingException {

		LOGGER.info("Fetching all the repos present");
		Map<String, List<Map<String, Object>>> response = new HashMap<>();
		try {
			response = middlewareApiService.getAllRepos();
		} catch (ApplicationException ae) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Map<String, List<Map<String, Object>>>>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/user/{userName}")
	public ResponseEntity<?> getAllReposForUserInJsonFormat(@PathVariable("userName") String userName) throws JsonProcessingException {

		LOGGER.info("Fetching all the repos for user -> {} ", userName);
		Map<String, List<Map<String, Object>>> response = null;
		try {
			response = middlewareApiService.getAllReposForUser(userName);
		} catch (ApplicationException ae) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Map<String, List<Map<String, Object>>>>(response, HttpStatus.OK);
	}
}
