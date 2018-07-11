package io.kang.unit_test.model_unit;

import io.kang.model.SignModel;
import io.kang.unit_test.singleton_object.model_testing.SignModelSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.SignVOSingleton;
import io.kang.vo.SignVO;
import org.junit.Assert;
import org.junit.Test;

public class SignModelUnitTest {
    private SignModel signModel = SignModelSingleton.INSTANCE.getInstance();
    private SignVO signVO = SignVOSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_model_test(){
        SignModel signModel = SignModel.builtToModel(this.signVO);
        Assert.assertEquals(signModel, this.signModel);
    }

    @Test
    public void built_to_vo_test(){
        SignVO signVO = SignModel.builtToVO(this.signModel);
        Assert.assertEquals(signVO, this.signVO);
    }
}
