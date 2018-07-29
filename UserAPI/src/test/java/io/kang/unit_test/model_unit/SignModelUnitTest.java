package io.kang.unit_test.model_unit;

import io.kang.dto.DetailDTO;
import io.kang.dto.UserDTO;
import io.kang.enumeration.Type;
import io.kang.model.SignModel;
import io.kang.unit_test.singleton_object.dto_unit.AgeDTOUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.CityDTOUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.DetailDTOUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.UserDTOUpdateSingleton;
import io.kang.unit_test.singleton_object.model_unit.SignModelSingleton;
import org.junit.Assert;
import org.junit.Test;

public class SignModelUnitTest {
    private SignModel signModel = SignModelSingleton.INSTANCE.getInstance();

    @Test
    public void is_password_equals_test(){
       Assert.assertTrue(signModel.isPasswordEquals());
    }

    @Test
    public void built_to_user_dto_test(){
        UserDTO userDTO = new UserDTO(null, signModel.getLoginId(), signModel.getNickname(), signModel.getMainPassword(), Type.USER);
        Assert.assertEquals(userDTO, SignModel.builtToUserDTO(signModel));
    }

    @Test
    public void built_to_user_dto_is_existed_test(){
        UserDTO userDTO = new UserDTO(1L, signModel.getLoginId(), signModel.getNickname(), signModel.getMainPassword(), Type.USER);
        Assert.assertEquals(userDTO, SignModel.builtToUserDTOIsExisted(1L, signModel, Type.USER));
    }

    @Test
    public void built_to_detail_dto_test(){
        DetailDTO detailDTO = new DetailDTO(null, UserDTOUpdateSingleton.INSTANCE.getInstance(), signModel.getName(), signModel.getEmail(), signModel.getHomeNumber(), signModel.getPhoneNumber(), CityDTOUpdateSingleton.INSTANCE.getInstance(), AgeDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(detailDTO, SignModel.builtToDetailDTO(signModel, UserDTOUpdateSingleton.INSTANCE.getInstance(), AgeDTOUpdateSingleton.INSTANCE.getInstance(), CityDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void built_to_detail_dto_is_existed_test(){
        DetailDTO detailDTO = new DetailDTO(1L, UserDTOUpdateSingleton.INSTANCE.getInstance(), signModel.getName(), signModel.getEmail(), signModel.getHomeNumber(), signModel.getPhoneNumber(), CityDTOUpdateSingleton.INSTANCE.getInstance(), AgeDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(detailDTO, SignModel.builtToDetailDTOExisted(1L, signModel, UserDTOUpdateSingleton.INSTANCE.getInstance(), AgeDTOUpdateSingleton.INSTANCE.getInstance(), CityDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void built_to_sign_model_test(){
        SignModel tmpSignModel = SignModel.builtToSignModel(DetailDTOUpdateSingleton.INSTANCE.getInstance(), AgeDTOUpdateSingleton.INSTANCE.getInstance(), CityDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertTrue(tmpSignModel != null);
    }
}
