package com.sindhu.springboot.controller;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.sindhu.springboot.model.Question;
import com.sindhu.springboot.services.SurveyService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=SurveyController.class, secure=false)
//in WebMvcTest we have an option of making secure authentication false for unit testing 
//because we are testing the code but not the security
//In integration test we use@SpringBootTest in which we should give 
//authentication details like username and password as mandatory to run the test case
public class SurveyControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SurveyService surveyService;

	@Test
	public void retrieveDetailsForQuestion(){
		Question mockQuestion = new Question("Question1", 
				"Largest Country in the World", "Russia", 
				Arrays.asList("India", "Russia", "United States", "China"));
		
		Mockito.when(
				surveyService.retrieveQuestion(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(mockQuestion);
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/surveys/Survey1/questions/Question1").accept(MediaType.APPLICATION_JSON);
		
		
		try {
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			String expected = "{id:Question1, description:Largest Country in the World, correctANswer:Russia}";
			
			JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	@Test
	public void createSurveyQuestion(){
		Question mockQuestion = new Question("10", 
				"Smallest Number", "1", 
				Arrays.asList("1", "2", "3", "4"));
		
		String questionJson = "{\"description\":\"Smallest Number\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]\"}";
		
		Mockito.when(
				surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class)))
				.thenReturn(mockQuestion);
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/surveys/Survey1/questions")
				.accept(MediaType.APPLICATION_JSON).content(questionJson).contentType(MediaType.APPLICATION_JSON);
		
		
		try {
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
			MockHttpServletResponse response = result.getResponse();
			System.out.println(response.getStatus());
			assertEquals(HttpStatus.OK.value(),response.getStatus());
			System.out.println(HttpStatus.OK.value());
			
			assertEquals("http://localhost/surveys/Survey1/questions/10", response.getHeader(HttpHeaders.LOCATION));
			//String expected = "{id:Question1, description:Largest Country in the World, correctANswer:Russia}";
			
			//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	*/

	
}

/*
 Main difference between integration testing and unit testing
 Integration Testing is done by launching the entire applictaion from end-to-end (all the models, controllers and services).
 We use @SpringBootTest(classes=Applicationname.class) to Integration Testing which launch the entire application 
 Authentication details should be given while testing.
 
 Unit Testing: In unit testing we no need to launch the entire application. we will launch only particular controller for specific class. and mocking particular service  
 We use @WebMvcTest(reuqiredControllerName.class) which launches that particular controller class for testing and 
 	we use @MockBean for the Service class to be used. 
 That is why the unit testing is faster compared to Integration Testing 
 In WebMvcTest we have an option of making secure authentication false for unit testing 
 	because we are testing the code but not the security.
 We have an option to make sercure as false for the unit testing using @WebMvcTest
*/