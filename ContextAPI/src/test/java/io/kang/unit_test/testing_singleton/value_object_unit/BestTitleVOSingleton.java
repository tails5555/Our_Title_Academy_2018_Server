package io.kang.unit_test.testing_singleton.value_object_unit;

import io.kang.vo.BestTitleVO;

public enum BestTitleVOSingleton {
    INSTANCE;
    private BestTitleVO bestTitleVO = new BestTitleVO("TITLE_USER_ID01", "TITLE_CONTEXT_01", 0L);
    public BestTitleVO getInstance(){
        if(this.bestTitleVO == null)
            return new BestTitleVO("TITLE_USER_ID01", "TITLE_CONTEXT_01", 0L);
        else return this.bestTitleVO;
    }
}
