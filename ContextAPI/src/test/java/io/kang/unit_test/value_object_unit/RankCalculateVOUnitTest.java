package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.testing_singleton.value_object_unit.RankCalculateVOSingleton;
import io.kang.vo.RankCalculateVO;
import org.junit.Assert;
import org.junit.Test;

public class RankCalculateVOUnitTest {
    @Test
    public void compare_to_test(){
        RankCalculateVO rankCalculateVO01 = new RankCalculateVO(1L, 0.0, 0.0);
        RankCalculateVO rankCalculateVO02 = new RankCalculateVO(1L, 0.1, 0.0);
        Assert.assertTrue(rankCalculateVO01.compareTo(rankCalculateVO02) > 0);
    }

    @Test
    public void built_to_vo_test(){
        RankCalculateVO rankCalculateVO = RankCalculateVO.builtToVO(1L, 0.0, 0.0);
        Assert.assertEquals(rankCalculateVO, RankCalculateVOSingleton.INSTANCE.getInstance());
    }
}
