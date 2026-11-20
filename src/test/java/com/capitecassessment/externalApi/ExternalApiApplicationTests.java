package com.capitecassessment.externalApi;


import com.capitecassessment.externalApi.controller.ExternalApiController;
import com.capitecassessment.externalApi.exception.GlobalExceptionHandler;
import com.capitecassessment.externalApi.model.AggregationTransactionDTO;
import com.capitecassessment.externalApi.service.MockDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;


@ExtendWith(MockitoExtension.class)
class ExternalApiApplicationTests {


	private WebTestClient webTestClient;

	@Mock
	private MockDataService dataService;

	@InjectMocks
	private ExternalApiController apiController;

	@BeforeEach
	void setUp() {
		// Register the controller AND its exception handler for isolated unit testing
		webTestClient = MockMvcWebTestClient.bindToController(apiController)
		 				.controllerAdvice(new GlobalExceptionHandler())
						.build();

	}

	@Test
	@DisplayName("fetch data tx")
	void getMockDataUsingRestClient() {
		String url = "http://localhost:8086/externalapi/v1/transactions/external?accountNumber=" + "\"1234567890\"";

    	//HTTPSTATUS 200 OK
		webTestClient.get()
				.uri(url)
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.consumeWith(result -> System.out.println("Response JSON: " + result.getResponseBody()));



		//HTTPSTATUS 500 INTERNAL SERVER ERROR
		//to test internal server 500 error use
		//comment above code and uncomment below code
/*
		webTestClient.get()
				.uri(url)
				.exchange()
				.expectStatus().is5xxServerError()
				.expectBody(String.class)
				.consumeWith(result -> System.out.println("Response JSON: " + result.getResponseBody()));
*/

	}

}
