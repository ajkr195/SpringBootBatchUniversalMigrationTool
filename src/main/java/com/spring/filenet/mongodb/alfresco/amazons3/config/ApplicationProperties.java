package com.spring.filenet.mongodb.alfresco.amazons3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("app")
@Configuration
public class ApplicationProperties {

	private AwsServices awsServices;

	public AwsServices getAwsServices() {
		return awsServices;
	}

	public void setAwsServices(AwsServices awsServices) {
		this.awsServices = awsServices;
	}

	public static class AwsServices {

		private String bucketName;

		public String getBucketName() {
			return bucketName;
		}

		public void setBucketName(String bucketName) {
			this.bucketName = bucketName;
		}

	}
}
