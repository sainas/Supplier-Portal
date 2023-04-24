package com.supplierportal.demo;


import com.supplierportal.demo.model.Enterprise;
import com.supplierportal.demo.model.Factory;
import com.supplierportal.demo.repository.EnterpriseRepository;
import com.supplierportal.demo.repository.FactoryRepository;
import com.supplierportal.demo.request.NewFactoryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.supplierportal.demo.model.EmployeeType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	@Autowired
	private FactoryRepository factoryRepository;

	@Test
	@Transactional
	void testGetEnterprise() {

		ResponseEntity<Enterprise> createResponse = restTemplate.getForEntity(
				"/enterprise/1", Enterprise.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		Optional<Factory> factory = factoryRepository.findById(1);
		assertTrue(factory.isPresent());
		assertThat(factory.get().getName()).isEqualTo("Test Factory 1");
		assertThat(factory.get().getEnterprise().getName()).isEqualTo("Enterprise A");
	}

	@Test
	void shouldCreateNewFactory() {
		Optional<Enterprise> e = enterpriseRepository.findById(1);
		System.out.println(e);
		NewFactoryRequest newFactory = new NewFactoryRequest(
				1,"Test Create Factory 1"
		);
		ResponseEntity<String> createResponse = restTemplate.postForEntity(
				"/enterprise/1/factory", newFactory, String.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		Optional<Factory> factory = factoryRepository.findById(1);
		assertTrue(factory.isPresent());
		assertThat(factory.get().getName()).isEqualTo("Test Create Factory 1");
		assertThat(factory.get().getEnterprise().getName()).isEqualTo("Enterprise A");

	}
}