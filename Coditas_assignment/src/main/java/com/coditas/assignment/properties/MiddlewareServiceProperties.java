package com.coditas.assignment.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("middleware")
public class MiddlewareServiceProperties {

	private String githubUrl;

	private String gitlabUrl;

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}

	public String getGitlabUrl() {
		return gitlabUrl;
	}

	public void setGitlabUrl(String gitlabUrl) {
		this.gitlabUrl = gitlabUrl;
	}

}
