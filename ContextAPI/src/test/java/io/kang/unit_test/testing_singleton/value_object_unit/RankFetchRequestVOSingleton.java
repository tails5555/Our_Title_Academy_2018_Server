package io.kang.unit_test.testing_singleton.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.CategoryDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.vo.RankFetchRequestVO;

public enum RankFetchRequestVOSingleton {
    INSTANCE;
    private RankFetchRequestVO rankFetchRequestVO = new RankFetchRequestVO(1L, 0.0, RequestDTOUpdateSingleton.INSTANCE.getInstance().getId(), CategoryDTOUpdateSingleton.INSTANCE.getInstance().getId(), CategoryDTOUpdateSingleton.INSTANCE.getInstance().getName(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getIntro(), "TITLE_CONTEXT_01", 0L, 0L);
    public RankFetchRequestVO getInstance(){
        if(this.rankFetchRequestVO == null)
            return new RankFetchRequestVO(1L, 0.0, RequestDTOUpdateSingleton.INSTANCE.getInstance().getId(), CategoryDTOUpdateSingleton.INSTANCE.getInstance().getId(), CategoryDTOUpdateSingleton.INSTANCE.getInstance().getName(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getIntro(), "TITLE_CONTEXT_01", 0L, 0L);
        else return this.rankFetchRequestVO;
    }
}
