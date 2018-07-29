package io.kang.unit_test.model_unit;

import io.kang.model.LoginModel;
import io.kang.unit_test.singleton_object.model_unit.LoginModelSingleton;
import org.junit.Assert;
import org.junit.Test;

public class LoginModelUnitTest {
    private LoginModel loginModel = LoginModelSingleton.INSTANCE.getInstance();
    private static final String LOGIN_ID = "LOGIN_ID01";
    private static final String PASSWORD = "LOGIN_PASSWORD01";

    @Test
    public void built_to_model_test(){
        LoginModel loginModel = LoginModel.builtToModel(LOGIN_ID, PASSWORD);
        Assert.assertEquals(loginModel, this.loginModel);
    }
}
