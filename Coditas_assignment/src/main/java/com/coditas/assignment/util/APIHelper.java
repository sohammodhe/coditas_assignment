package com.coditas.assignment.util;

import org.springframework.util.StringUtils;

public class APIHelper {

	private static final String USERS = "users";

	private static final String REPOS = "repos";

	private static final String REPOSITORIES = "repositories";

	/**
	 * This utility method is used to generate the github URL for all public repos
	 * and user specific public repos
	 * 
	 * @param gitHubUrl
	 * @param userName
	 * @return
	 */
	public static String generateGitHubUrl(String gitHubUrl, String userName) {

		if (StringUtils.isEmpty(userName)) {
			return String.join("/", gitHubUrl, REPOSITORIES);// public repos
		} else {
			return String.join("/", gitHubUrl, USERS, userName, REPOS);// user specific public repos
		}
	}
}
