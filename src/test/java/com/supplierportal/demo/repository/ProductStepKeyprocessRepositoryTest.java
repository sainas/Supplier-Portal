package com.supplierportal.demo.repository;

import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductStepKeyprocessRepositoryTest {
    @Autowired
    private ProductStepKeyprocessRepository productStepKeyprocessRepository;

    @Autowired
    private EnterpriseKeyprocessEquipmentRepository enterpriseKeyprocessEquipmentRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Test
    public void testGetProductStepKeyprocess() {
        System.out.println(1);
        List<Tuple> results = enterpriseKeyprocessEquipmentRepository.findEnterpriseIdAndProductStepIdByProductIdAndIsBottleneckIsTrue(1);
        System.out.println(1);
        System.out.println(1);
        System.out.println(results);



    }
}