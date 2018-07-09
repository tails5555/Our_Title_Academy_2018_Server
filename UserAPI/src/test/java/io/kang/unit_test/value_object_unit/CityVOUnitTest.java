package io.kang.unit_test.value_object_unit;

import io.kang.domain.City;
import io.kang.unit_test.singleton_object.repository_testing.CityUpdateSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.CityVOSingleton;
import io.kang.vo.CityVO;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CityVOUnitTest {
    private static City city = CityUpdateSingleton.INSTANCE.getInstance();
    private static CityVO cityVO = CityVOSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_vo_test() throws IOException {
        CityVO cityVO = CityVO.builtToVO(this.city);
        Assert.assertEquals(cityVO, this.cityVO);
    }

    @Test
    public void built_to_domain_test() throws IOException {
        City city = CityVO.builtToDomain(this.cityVO);
        Assert.assertEquals(city, this.city);
    }
}
