package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.testing_singleton.value_object_unit.BattleSocketVOSingleton;
import io.kang.vo.BattleSocketVO;
import org.junit.Assert;
import org.junit.Test;

public class BattleSocketVOUnitTest {
    @Test
    public void built_to_vo_test(){
        BattleSocketVO battleSocketVO = BattleSocketVO.builtToVO(1L, "TITLE_USER_ID01", null, false, "TITLE_CONTEXT_01");
        Assert.assertEquals(battleSocketVO, BattleSocketVOSingleton.INSTANCE.getInstance());
    }
}
