package io.kang.unit_test.testing_singleton.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.vo.BattleFetchRequestVO;

public enum BattleFetchRequestVOSingleton {
    INSTANCE;
    private BattleFetchRequestVO battleFetchRequestVO = new BattleFetchRequestVO(RequestDTOUpdateSingleton.INSTANCE.getInstance(), 0L, 0L, false, false);
    public BattleFetchRequestVO getInstance(){
        if(this.battleFetchRequestVO == null)
            return new BattleFetchRequestVO(RequestDTOUpdateSingleton.INSTANCE.getInstance(), 0L, 0L, false, false);
        else return this.battleFetchRequestVO;
    }
}
