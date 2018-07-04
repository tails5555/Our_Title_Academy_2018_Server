package io.kang.unit_test.domain_unit;

import io.kang.domain.Age;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class AgeUnitTest {

    private static final long ID = 1L;
    private static final String NAME = "AGE01";

    @Test
    public void idGetterAndSetterTesting() throws IOException {
        Age age = new Age();
        age.setId(ID);
        long id = age.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void nameGetterAndSetterTesting() throws IOException{
        Age age = new Age();
        age.setName(NAME);
        String name = age.getName();
        Assert.assertEquals(name, NAME);
    }

    @Test
    public void equalsTesting() throws IOException{
        Age age = new Age();
        age.setId(ID);
        age.setName(NAME);

        Age sameAge = new Age(ID, NAME);
        Assert.assertTrue(sameAge.equals(age));
    }

    @Test
    public void toStringTesting() throws IOException{
        Age age = new Age();
        age.setId(ID);
        age.setName(NAME);

        String string = age.toString();
        Assert.assertEquals(string, "Age(id=1, name=AGE01)");
    }
}
