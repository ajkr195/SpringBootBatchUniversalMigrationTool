package com.spring.filenet.mongodb.alfresco.amazons3.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AlfrescoRestTemplate {

	public static void main(String[] args) {
		getFolderContentList();
//		somePOSTExample();
//		somePUTExample();
	}

	private static void getFolderContentList() {
		String uri = "http://someservername:8080/alfresco/api/-default-/public/alfresco/versions/1/nodes/12341234-1234-1234-1234-123412341234/children";

		RestTemplate restTemplate = new RestTemplate();

		try {
			// HttpHeaders
			HttpHeaders headers = new HttpHeaders();

			// SetContentType
			headers.setContentType(MediaType.APPLICATION_JSON);

			// SetBasicAuthentication
			headers.setBasicAuth("admin", "admin");

			// GET Request
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			// GET Method (response)
			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

			// Get response for GET method
			System.out.println("Response status - " + response.getStatusCode());
			System.out.println("Response has body? - " + response.hasBody());
			System.out.println("Response body - " + response.getBody());

		} catch (Exception e) {
			System.out.println("Error occurred...");
			e.printStackTrace();
		}

	}

	private static void somePOSTExample() {
		String uri = "http://someservername:8080/alfresco/api/-default-/public/gs/versions/1/files/12341234-1234-1234-1234-123412341234/declare?hideRecord=false";

		RestTemplate restTemplate = new RestTemplate();

		try {
			// HttpHeaders
			HttpHeaders headers = new HttpHeaders();

			// SetContentType
			headers.setContentType(MediaType.APPLICATION_JSON);

			// SetBasicAuthentication
			headers.setBasicAuth("admin", "admin");

			// POST Request
			HttpEntity<String> request = new HttpEntity<String>(headers);
			// POST Method (response)
			ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);

			// Get response for GET method
			System.out.println("Response status - " + response.getStatusCode());
			System.out.println("Response has body? - " + response.hasBody());
			System.out.println("Response body - " + response.getBody());

		} catch (Exception e) {
			System.out.println("Error occurred...");
			e.printStackTrace();
		}

	}

	private static String somePUTExample() {
		String uri = "http://someservername:8080/alfresco/api/-default-/public/gs/versions/1/records/";

		RestTemplate restTemplate = new RestTemplate();

		try {
			// HttpHeaders
			HttpHeaders headers = new HttpHeaders();

			// SetContentType
			headers.setContentType(MediaType.APPLICATION_JSON);

			// SetBasicAuthentication
			headers.setBasicAuth("admin", "admin");
			
			String requestJson = "{\"admin\":{ \"cm:title\" : \"New Title Using RESTAPI\", \"some:custom_meta\" : \"123456\"}}";

			// POST Request
			HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
			// POST Method (response)
			HttpEntity<String[]> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, String[].class);

			// Get response for GET method
			System.out.println("Response has body? - " + response.hasBody());
			System.out.println("Response body - " + response.getBody());

			return restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class).getBody();

		} catch (Exception e) {
			System.out.println("Error occurred...");
			e.printStackTrace();
		}
		return null;

	}

}
