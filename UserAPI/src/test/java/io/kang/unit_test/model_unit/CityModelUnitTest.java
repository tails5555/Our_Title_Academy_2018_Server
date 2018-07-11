package io.kang.unit_test.model_unit;

import io.kang.model.CityModel;
import io.kang.unit_test.singleton_object.model_testing.CityModelSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.CityVOSingleton;
import io.kang.vo.CityVO;
import org.junit.Assert;
import org.junit.Test;

public class CityModelUnitTest {
    private CityModel cityModel = CityModelSingleton.INSTANCE.getInstance();
    private CityVO cityVO = CityVOSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_model_test(){
        CityModel cityModel = CityModel.builtToModel(this.cityVO);
        Assert.assertEquals(cityModel, this.cityModel);
    }

    @Test
    public void built_to_vo_test(){
        CityVO cityVO = CityModel.builtToVO(this.cityModel);
        Assert.assertEquals(cityVO, this.cityVO);
    }
}
