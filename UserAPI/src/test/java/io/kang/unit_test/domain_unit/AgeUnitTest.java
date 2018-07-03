package io.kang.unit_test.domain_unit;

import io.kang.domain.Age;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@SpringBootTest(classes = io.kang.main.UserApiApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AgeUnitTest {

    private JacksonTester<Age> jacksonTester;
    private static final long ID = 1L;
    private static final String NAME = "AGE01";
    private static final String JSON_TO_DESERIALIZE = String.format("{\"id\":%d,\"name\":\"%s\"}", ID, NAME);
    private Age age;

    @Before
    public void setup() throws ParseException {
        age = new Age(ID, NAME);
    }

    @Test
    public void idSerializes() throws IOException {
        assertThat(this.jacksonTester.write(age))
                .extractingJsonPathStringValue("@.id")
                .isEqualTo(ID);
    }

    @Test
    public void nameSerializes() throws IOException {
        assertThat(this.jacksonTester.write(age))
                .extractingJsonPathStringValue("@.name")
                .isEqualTo(NAME);
    }

    @Test
    public void idDeserializes() throws IOException {
        assertThat(this.jacksonTester.parseObject(JSON_TO_DESERIALIZE).getId()).isEqualTo(ID);
    }

    @Test
    public void nameDeserializes() throws IOException {
        assertThat(this.jacksonTester.parseObject(JSON_TO_DESERIALIZE).getName()).isEqualTo(NAME);
    }
}
