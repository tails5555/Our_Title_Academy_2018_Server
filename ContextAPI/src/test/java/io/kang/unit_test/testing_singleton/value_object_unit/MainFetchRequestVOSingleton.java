package io.kang.unit_test.testing_singleton.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.vo.MainFetchRequestVO;

import java.util.Arrays;

public enum MainFetchRequestVOSingleton {
    INSTANCE;
    private MainFetchRequestVO mainFetchRequestVO = new MainFetchRequestVO(RequestDTOUpdateSingleton.INSTANCE.getInstance(), Arrays.asList(BestTitleVOSingleton.INSTANCE.getInstance()), 0L, 0L, false, false);
    public MainFetchRequestVO getInstance(){
        if(this.mainFetchRequestVO == null)
            return new MainFetchRequestVO(RequestDTOUpdateSingleton.INSTANCE.getInstance(), Arrays.asList(BestTitleVOSingleton.INSTANCE.getInstance()), 0L, 0L, false, false);
        else return this.mainFetchRequestVO;
    }
}
