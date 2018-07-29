package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.singleton_object.dto_unit.DetailDTOUpdateSingleton;
import io.kang.unit_test.singleton_object.vo_unit.PrincipalVOSingleton;
import io.kang.vo.PrincipalVO;
import org.junit.Assert;
import org.junit.Test;

public class PrincipalVOUnitTest {
    @Test
    public void built_to_vo_test(){
        PrincipalVO principalVO = PrincipalVO.builtToVO(DetailDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(principalVO, PrincipalVOSingleton.INSTANCE.getInstance());
    }
}
