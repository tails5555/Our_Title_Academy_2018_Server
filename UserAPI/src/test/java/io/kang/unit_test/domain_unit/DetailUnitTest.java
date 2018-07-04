package io.kang.unit_test.domain_unit;

import io.kang.domain.Age;
import io.kang.domain.City;
import io.kang.domain.Detail;
import io.kang.domain.User;
import io.kang.unit_test.domain_unit.singleton_object.AgeSingleton;
import io.kang.unit_test.domain_unit.singleton_object.CitySingleton;
import io.kang.unit_test.domain_unit.singleton_object.UserSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DetailUnitTest {
    private static final long ID = 1L;
    private static final User USER = UserSingleton.INSTANCE.getInstance();
    private static final String NAME = "DETAIL_NAME01";
    private static final String EMAIL = "DETAIL_EMAIL01";
    private static final String HOME_NUMBER = "DETAIL_HOME_NUMBER01";
    private static final String PHONE_NUMBER = "DETAIL_PHONE_NUMBER01";
    private static final City CITY = CitySingleton.INSTANCE.getInstance();
    private static final Age AGE = AgeSingleton.INSTANCE.getInstance();

    @Test
    public void idGetterAndSetterTesting() throws IOException {
        Detail detail = new Detail();
        detail.setId(ID);
        long id = detail.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void userGetterAndSetterTesting() throws IOException {
        Detail detail = new Detail();
        detail.setUser(USER);
        User user = detail.getUser();
        Assert.assertEquals(user, USER);
    }

    @Test
    public void nameGetterAndSetterTesting() throws IOException {
        Detail detail = new Detail();
        detail.setName(NAME);
        String name = detail.getName();
        Assert.assertEquals(name, NAME);
    }

    @Test
    public void emailGetterAndSetterTesting() throws IOException {
        Detail detail = new Detail();
        detail.setEmail(EMAIL);
        String email = detail.getEmail();
        Assert.assertEquals(email, EMAIL);
    }

    @Test
    public void homeNumberGetterAndSetterTesting() throws IOException {
        Detail detail = new Detail();
        detail.setHomeNumber(HOME_NUMBER);
        String homeNumber = detail.getHomeNumber();
        Assert.assertEquals(homeNumber, HOME_NUMBER);
    }

    @Test
    public void phoneNumberGetterAndSetterTesting() throws IOException{
        Detail detail = new Detail();
        detail.setPhoneNumber(PHONE_NUMBER);
        String phoneNumber = detail.getPhoneNumber();
        Assert.assertEquals(phoneNumber, PHONE_NUMBER);
    }

    @Test
    public void cityGetterAndSetterTesting() throws IOException{
        Detail detail = new Detail();
        detail.setCity(CITY);
        City city = detail.getCity();
        Assert.assertEquals(city, CITY);
    }

    @Test
    public void ageGetterAndSetterTesting() throws IOException{
        Detail detail = new Detail();
        detail.setAge(AGE);
        Age age = detail.getAge();
        Assert.assertEquals(age, AGE);
    }

    @Test
    public void equalsTesting() throws IOException{
        Detail detail = new Detail();
        detail.setId(ID);
        detail.setUser(USER);
        detail.setName(NAME);
        detail.setEmail(EMAIL);
        detail.setHomeNumber(HOME_NUMBER);
        detail.setPhoneNumber(PHONE_NUMBER);
        detail.setCity(CITY);
        detail.setAge(AGE);

        Detail sameDetail = new Detail(ID, USER, NAME, EMAIL, HOME_NUMBER, PHONE_NUMBER, CITY, AGE);
        Assert.assertTrue(sameDetail.equals(detail));
    }

    @Test
    public void toStringTesting() throws IOException{
        Detail detail = new Detail();
        detail.setId(ID);
        detail.setUser(USER);
        detail.setName(NAME);
        detail.setEmail(EMAIL);
        detail.setHomeNumber(HOME_NUMBER);
        detail.setPhoneNumber(PHONE_NUMBER);
        detail.setCity(CITY);
        detail.setAge(AGE);

        String string = detail.toString();
        String cmpResult = String.format("Detail(id=%d, user=%s, name=%s, email=%s, homeNumber=%s, phoneNumber=%s, city=%s, age=%s)", ID, USER, NAME, EMAIL, HOME_NUMBER, PHONE_NUMBER, CITY, AGE);
        Assert.assertEquals(string, cmpResult);
    }
}
