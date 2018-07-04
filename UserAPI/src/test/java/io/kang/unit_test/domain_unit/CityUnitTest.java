package io.kang.unit_test.domain_unit;

import io.kang.domain.City;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CityUnitTest {
    private static final long ID = 1L;
    private static final String NAME = "CITY01";

    @Test
    public void idGetterAndSetterTesting() throws IOException {
        City city = new City();
        city.setId(ID);
        long id = city.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void nameGetterAndSetterTesting() throws IOException{
        City city = new City();
        city.setName(NAME);
        String name = city.getName();
        Assert.assertEquals(name, NAME);
    }

    @Test
    public void equalsTesting() throws IOException{
        City city = new City();
        city.setId(ID);
        city.setName(NAME);

        City sameCity = new City(ID, NAME);
        Assert.assertTrue(sameCity.equals(city));
    }

    @Test
    public void toStringTesting() throws IOException{
        City city = new City();
        city.setId(ID);
        city.setName(NAME);

        String string = city.toString();
        Assert.assertEquals(string, "City(id=1, name=CITY01)");
    }
}
