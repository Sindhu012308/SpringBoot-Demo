package com.sindhu.springboot.controller;

import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;
import com.sindhu.springboot.Application;
import com.sindhu.springboot.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

	@LocalServerPort
	private int port;
	
	
	//TestRestTemplate is used to invoke rest 
	TestRestTemplate template = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	private String createHttpAuthenticationHeaderValue(String userId, String password) {
		//userID, password,Basic-Authentication
		//"Authorization", "Basic",+ BaseEncoding(userId + ":" + password)
		
		String auth = userId + ":" + password;
		
		byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
		
		String headerValue = "Basic" + new String(encodedAuth);
		
		return headerValue;
	}
			
	@Before
	public void before() {
		//Accept : application/json in headers for json format of output.
		// but restTemplate doesnot directly support any such header type to invoke. so we map using HttpEntity in RestTemplate
		headers.add("Authorization", createHttpAuthenticationHeaderValue("user1","secret1"));
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

	}
	/*
	 @Test 
	 public void testJsonAssert(){
	 	String actual="{id:1, name:Sindhu}";
	 	JSONAssert.assertEquals("{id:1}",actual,false);
	 	//while comparing spaces will be ignored before and after semi-colon in json format
	 }
	 */
	
	@Test
	public void testRetrieveSurveyQuestion() {
	
		System.out.println("Port Number"+ port);
	
		// We need to get the response of the url request in string format. so we use String.class
		//String output = template.getForObject(url,String.class);
		
		HttpEntity entity = new HttpEntity(null, headers);// create the entity
		
		ResponseEntity<String> response = template.exchange(createURLWithPort("/surveys/Survey1/questions/Question1"), HttpMethod.GET, entity , String.class);
		
		System.out.println("Response" + response.getBody());
		
		String expected = "{id:Question1, description:Largest Country in the World, correctANswer:Russia}";
		
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
		// for JSONAssert.assertEquals(expected, actual, strict); By giving Strict as true both expected and actual should strictly match.
		//if it is false then expected can be a part of actual response.
	
	}
	
	@Test
	public void testAddQuestion() {
		
		Question question = new Question("DoesntMatter", "Question1", "Russia", Arrays.asList("India", "Russia", "United States", "China"));

		HttpEntity entity = new HttpEntity<Question>(question, headers);// create the entity
		
		ResponseEntity<String> response = template.exchange(createURLWithPort("/surveys/Survey1/questions"), 
				HttpMethod.POST, entity , String.class);
		
		String actual=response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		
		assertTrue(actual.contains("/surveys/Survey1/questions/"));
		
		System.out.println("Actual : " + actual);

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:"+port+uri;
	}
}

/*
 Part 1: Initialize and launch Spring Boot Application 
 		@RunWith(SpringRunner.class)
 		@SpringBootTest(clases = Application.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
 		@LoaclServerPort
 		private int port;
 Part 2: Invoke the url /surveys/Survey1/questions/Question1
 		private TestRestTemplate template = new TestRestTemplate();
*/