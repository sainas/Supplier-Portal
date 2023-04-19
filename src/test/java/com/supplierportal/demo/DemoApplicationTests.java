package com.supplierportal.demo;


import com.supplierportal.demo.model.Enterprise;
import com.supplierportal.demo.model.Factory;
import com.supplierportal.demo.repository.EnterpriseRepository;
import com.supplierportal.demo.repository.FactoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	@Autowired
	private FactoryRepository factoryRepository;

	@Test
	void shouldCreateNewFactory() {
		Enterprise enterprise = new Enterprise(1, "Test Enterprise");
		enterpriseRepository.save(enterprise);
		Factory newFactory = new Factory("Test Factory 1");
		ResponseEntity<String> createResponse = restTemplate.postForEntity(
				"/enterprise/1/factory", newFactory, String.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		Optional<Factory> factory = factoryRepository.findById(1);

		assertThat(factory.get().getName()).isEqualTo("Test Factory 1");

	}
}