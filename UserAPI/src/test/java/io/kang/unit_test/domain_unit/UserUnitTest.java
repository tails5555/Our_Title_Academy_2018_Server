package io.kang.unit_test.domain_unit;

import io.kang.domain.User;
import io.kang.enumeration.Type;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class UserUnitTest {
    private static final long ID = 1L;
    private static final String LOGIN_ID = "USER_LOGIN_ID01";
    private static final String NICKNAME = "USER_NICKNAME01";
    private static final String PASSWORD = "USER_PASSWORD01";
    private static final Type USER_TYPE = Type.USER;

    @Test
    public void idGetterAndSetterTesting() throws IOException {
        User user = new User();
        user.setId(ID);
        long id = user.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void loginIdGetterAndSetterTesting() throws IOException {
        User user = new User();
        user.setLoginId(LOGIN_ID);
        String loginId = user.getLoginId();
        Assert.assertEquals(loginId, LOGIN_ID);
    }

    @Test
    public void nicknameGetterAndSetterTesting() throws IOException{
        User user = new User();
        user.setNickname(NICKNAME);
        String nickname = user.getNickname();
        Assert.assertEquals(nickname, NICKNAME);
    }

    @Test
    public void passwordGetterAndSetterTesting() throws IOException{
        User user = new User();
        user.setPassword(PASSWORD);
        String password = user.getPassword();
        Assert.assertEquals(password, PASSWORD);
    }

    @Test
    public void typeGetterAndSetterTesting() throws IOException{
        User user = new User();
        user.setUserType(Type.USER);
        Type userType = user.getUserType();
        Assert.assertEquals(USER_TYPE, userType);
    }

    @Test
    public void equalsTesting() throws IOException{
        User user = new User();
        user.setId(ID);
        user.setLoginId(LOGIN_ID);
        user.setNickname(NICKNAME);
        user.setPassword(PASSWORD);
        user.setUserType(USER_TYPE);

        User sameUser = new User(ID, LOGIN_ID, NICKNAME, PASSWORD, USER_TYPE);

        Assert.assertTrue(sameUser.equals(user));
    }

    @Test
    public void toStringTesting() throws IOException{
        User user = new User();
        user.setId(ID);
        user.setLoginId(LOGIN_ID);
        user.setNickname(NICKNAME);
        user.setPassword(PASSWORD);
        user.setUserType(USER_TYPE);

        String string = user.toString();
        String cmpResult = String.format("User(id=%d, loginId=%s, nickname=%s, password=%s, userType=%s)", ID, LOGIN_ID, NICKNAME, PASSWORD, USER_TYPE);
        Assert.assertEquals(string, cmpResult);
    }
}
