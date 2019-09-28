package com.coditas.assignment.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtility {

	public static Map<String, List<Map<String, Object>>> getDefaultResponseForFetchRepos()
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		File resourcesDirectory = new File("src/test/resources/defaultGitHubResponse.json");
		Map<String, List<Map<String, Object>>> defaultResponse = (Map<String, List<Map<String, Object>>>) mapper
				.readValue(resourcesDirectory, Object.class);
		return defaultResponse;
	}
}
