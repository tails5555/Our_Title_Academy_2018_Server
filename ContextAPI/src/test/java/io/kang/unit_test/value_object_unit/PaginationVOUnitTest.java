package io.kang.unit_test.value_object_unit;

import io.kang.unit_test.testing_singleton.model_unit.PaginationModelSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.BriefFetchRequestVOSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.PaginationVOSingleton;
import io.kang.vo.PaginationVO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class PaginationVOUnitTest {
    @Test
    public void built_to_vo_test(){
        PaginationVO paginationVO = PaginationVO.builtToVO(PaginationModelSingleton.INSTANCE.getInstance(), Arrays.asList(BriefFetchRequestVOSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(paginationVO, PaginationVOSingleton.INSTANCE.getInstance());
    }
}
