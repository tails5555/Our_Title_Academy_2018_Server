package io.kang.unit_test.testing_singleton.value_object_unit;

import io.kang.enumeration.Type;
import io.kang.unit_test.testing_singleton.dto_unit.CategoryDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.vo.SearchResultVO;

public enum SearchResultVORequestSingleton {
    INSTANCE;
    private SearchResultVO searchResultVO = new SearchResultVO(1L, Type.REQUEST, CategoryDTOUpdateSingleton.INSTANCE.getInstance().getId(), CategoryDTOUpdateSingleton.INSTANCE.getInstance().getName(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getIntro(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getContext(), 0L, 0L);
    public SearchResultVO getInstance(){
        if(this.searchResultVO == null)
            return new SearchResultVO(1L, Type.REQUEST, CategoryDTOUpdateSingleton.INSTANCE.getInstance().getId(), CategoryDTOUpdateSingleton.INSTANCE.getInstance().getName(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getIntro(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getContext(), 0L, 0L);
        else return this.searchResultVO;
    }
}
