package io.kang.unit_test.testing_singleton.value_object_unit;

import io.kang.unit_test.testing_singleton.model_unit.PaginationModelSingleton;
import io.kang.vo.PaginationVO;

import java.util.Arrays;

public enum PaginationVOSingleton {
    INSTANCE;
    private PaginationVO paginationVO = new PaginationVO(PaginationModelSingleton.INSTANCE.getInstance(), Arrays.asList(BriefFetchRequestVOSingleton.INSTANCE.getInstance()));
    public PaginationVO getInstance(){
        if(this.paginationVO == null)
            return new PaginationVO(PaginationModelSingleton.INSTANCE.getInstance(), Arrays.asList(BriefFetchRequestVOSingleton.INSTANCE.getInstance()));
        else return this.paginationVO;
    }
}
