package io.kang.unit_test.testing_singleton.value_object_unit;

import io.kang.vo.BattleSocketVO;

public enum BattleSocketVOSingleton {
    INSTANCE;
    private BattleSocketVO battleSocketVO = new BattleSocketVO(1L, "TITLE_USER_ID01", null, false, "TITLE_CONTEXT_01");
    public BattleSocketVO getInstance(){
        if(this.battleSocketVO == null)
            return new BattleSocketVO(1L, "TITLE_USER_ID01", null, false, "TITLE_CONTEXT_01");
        else return this.battleSocketVO;
    }
}
