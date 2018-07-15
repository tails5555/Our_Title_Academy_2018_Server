package io.kang.unit_test.model_unit;

import io.kang.model.SignModel;
import io.kang.unit_test.singleton_object.model_testing.SignModelSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.AgeVOSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.CityVOSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.DetailVOSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.UserVOSingleton;
import io.kang.vo.DetailVO;
import io.kang.vo.UserVO;
import org.junit.Assert;
import org.junit.Test;

public class SignModelUnitTest {
    private SignModel signModel = SignModelSingleton.INSTANCE.getInstance();

    @Test
    public void is_password_equals_test(){
       Assert.assertTrue(signModel.isPasswordEquals());
    }

    @Test
    public void built_to_user_vo_test(){
        UserVO userVO = new UserVO(null, signModel.getLoginId(), signModel.getNickname(), signModel.getMainPassword(), signModel.getType());
        Assert.assertEquals(userVO, SignModel.builtToUserVO(signModel));
    }

    @Test
    public void built_to_detail_vo_test(){
        DetailVO detailVO = new DetailVO(null, UserVOSingleton.INSTANCE.getInstance(), signModel.getName(), signModel.getEmail(), signModel.getHomeNumber(), signModel.getPhoneNumber(), CityVOSingleton.INSTANCE.getInstance(), AgeVOSingleton.INSTANCE.getInstance());
        Assert.assertEquals(detailVO, SignModel.builtToDetailVO(signModel, UserVOSingleton.INSTANCE.getInstance(), AgeVOSingleton.INSTANCE.getInstance(), CityVOSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void built_to_sign_model_test(){
        SignModel tmpSignModel = SignModel.builtToSignModel(DetailVOSingleton.INSTANCE.getInstance(), AgeVOSingleton.INSTANCE.getInstance(), CityVOSingleton.INSTANCE.getInstance());
        Assert.assertTrue(tmpSignModel != null);
    }
}
