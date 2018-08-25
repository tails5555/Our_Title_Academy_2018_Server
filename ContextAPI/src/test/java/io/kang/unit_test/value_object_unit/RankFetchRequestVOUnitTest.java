package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TodayRankDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.RankFetchRequestVOSingleton;
import io.kang.vo.RankFetchRequestVO;
import org.junit.Assert;
import org.junit.Test;

public class RankFetchRequestVOUnitTest {
    @Test
    public void built_to_vo_test(){
        RankFetchRequestVO rankFetchRequestVO = RankFetchRequestVO.builtToVO(RequestDTOUpdateSingleton.INSTANCE.getInstance(), TodayRankDTOUpdateSingleton.INSTANCE.getInstance(), TitleDTOUpdateSingleton.INSTANCE.getInstance(), 0L, 0L);
        Assert.assertEquals(rankFetchRequestVO, RankFetchRequestVOSingleton.INSTANCE.getInstance());
    }
}
