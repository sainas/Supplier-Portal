package com.supplierportal.demo.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class EnterpriseJsonTest {

    @Autowired
    private JacksonTester<Enterprise> json;

    @Test
    public void enterpriseSerializationTest() throws IOException {
        Enterprise enterprise = new Enterprise(1, "Test Enterprise");
        assertThat(json.write(enterprise)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(1);
        assertThat(json.write(enterprise)).extractingJsonPathStringValue("@.name")
                .isEqualTo("Test Enterprise");
    }

    @Test
    public void enterpriseDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 99,
                    "name": "Test Enterprise"
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new Enterprise(99, "Test Enterprise"));
    }
}
