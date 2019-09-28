package com.coditas.assignment.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.coditas.assignment.exception.ApplicationException;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public interface MiddlewareApiService {

	public Map<String, List<Map<String, Object>>> getAllRepos() throws ApplicationException, JsonProcessingException;

	public Map<String, List<Map<String, Object>>> getAllReposForUser(String userName) throws ApplicationException, JsonProcessingException;

}
