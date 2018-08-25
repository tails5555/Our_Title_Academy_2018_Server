package io.kang.unit_test.testing_singleton.value_object_unit;

import io.kang.vo.RankCalculateVO;

public enum RankCalculateVOSingleton {
    INSTANCE;
    private RankCalculateVO rankCalculateVO = new RankCalculateVO(1L, 0.0, 0.0);
    public RankCalculateVO getInstance(){
        if(this.rankCalculateVO == null)
            return new RankCalculateVO(1L, 0.0, 0.0);
        else return this.rankCalculateVO;
    }
}
