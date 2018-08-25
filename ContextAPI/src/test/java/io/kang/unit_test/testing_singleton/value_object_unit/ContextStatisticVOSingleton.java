package io.kang.unit_test.testing_singleton.value_object_unit;

import io.kang.vo.ContextStatisticVO;

public enum ContextStatisticVOSingleton {
    INSTANCE;
    private ContextStatisticVO contextStatisticVO = new ContextStatisticVO("CATEGORY_NAME01", 0L, 0L);
    public ContextStatisticVO getInstance(){
        if(this.contextStatisticVO == null)
            return new ContextStatisticVO("CATEGORY_NAME01", 0L, 0L);
        else return this.contextStatisticVO;
    }
}
