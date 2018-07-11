package io.kang.unit_test.model_unit;

import io.kang.model.AgeModel;
import io.kang.unit_test.singleton_object.model_testing.AgeModelSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.AgeVOSingleton;
import io.kang.vo.AgeVO;
import org.junit.Assert;
import org.junit.Test;

public class AgeModelUnitTest {
    private AgeModel ageModel = AgeModelSingleton.INSTANCE.getInstance();
    private AgeVO ageVO = AgeVOSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_model_test(){
        AgeModel ageModel = AgeModel.builtToModel(this.ageVO);
        Assert.assertEquals(ageModel, this.ageModel);
    }

    @Test
    public void built_to_vo_test(){
        AgeVO ageVO = AgeModel.builtToVO(this.ageModel);
        Assert.assertEquals(ageVO, this.ageVO);
    }
}
