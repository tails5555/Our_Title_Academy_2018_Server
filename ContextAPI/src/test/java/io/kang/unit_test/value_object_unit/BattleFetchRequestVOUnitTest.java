package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.BattleFetchRequestVOSingleton;
import io.kang.vo.BattleFetchRequestVO;
import org.junit.Assert;
import org.junit.Test;

public class BattleFetchRequestVOUnitTest {
    @Test
    public void built_to_vo_test(){
        BattleFetchRequestVO battleFetchRequestVO = BattleFetchRequestVO.builtToVO(RequestDTOUpdateSingleton.INSTANCE.getInstance(), 0L, 0L, false, false);
        Assert.assertEquals(battleFetchRequestVO, BattleFetchRequestVOSingleton.INSTANCE.getInstance());
    }
}
