package com.mab.merchantapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="env")
public class EnvProfile {
	private String backendServerUrl;
	private String uploadDir;
	private String profileDataDir;
	private String contentDataDir;
	private String publicServerUrl;
	private String dbUrl;
	private String dbUsername;
	private String dbPassword;
	private String dbSchema;
	
	public String getBackendServerUrl() {
		return backendServerUrl;
	}
	public void setBackendServerUrl(String backendServerUrl) {
		this.backendServerUrl = backendServerUrl;
	}
	public String getUploadDir() {
		return uploadDir;
	}
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	public String getProfileDataDir() {
		return profileDataDir;
	}
	public void setProfileDataDir(String profileDataDir) {
		this.profileDataDir = profileDataDir;
	}
	public String getPublicServerUrl() {
		return publicServerUrl;
	}
	public void setPublicServerUrl(String publicServerUrl) {
		this.publicServerUrl = publicServerUrl;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getDbUsername() {
		return dbUsername;
	}
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public String getDbSchema() {
		return dbSchema;
	}
	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}
	public String getContentDataDir() {
		return contentDataDir;
	}
	public void setContentDataDir(String contentDataDir) {
		this.contentDataDir = contentDataDir;
	}
	
}
