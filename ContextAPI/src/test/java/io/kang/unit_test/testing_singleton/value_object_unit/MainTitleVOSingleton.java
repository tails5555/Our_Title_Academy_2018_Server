package io.kang.unit_test.testing_singleton.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.CategoryDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleDTOUpdateSingleton;
import io.kang.vo.MainTitleVO;

public enum MainTitleVOSingleton {
    INSTANCE;
    private MainTitleVO mainTitleVO = new MainTitleVO(TitleDTOUpdateSingleton.INSTANCE.getInstance().getId(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getId(), CategoryDTOUpdateSingleton.INSTANCE.getInstance().getId(), CategoryDTOUpdateSingleton.INSTANCE.getInstance().getName(), TitleDTOUpdateSingleton.INSTANCE.getInstance().getContext(), TitleDTOUpdateSingleton.INSTANCE.getInstance().getUserId(), TitleDTOUpdateSingleton.INSTANCE.getInstance().getWrittenDate(), 0L, 0L, false, false);
    public MainTitleVO getInstance(){
        if(this.mainTitleVO == null)
            return new MainTitleVO(TitleDTOUpdateSingleton.INSTANCE.getInstance().getId(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getId(), CategoryDTOUpdateSingleton.INSTANCE.getInstance().getId(), CategoryDTOUpdateSingleton.INSTANCE.getInstance().getName(), TitleDTOUpdateSingleton.INSTANCE.getInstance().getContext(), TitleDTOUpdateSingleton.INSTANCE.getInstance().getUserId(), TitleDTOUpdateSingleton.INSTANCE.getInstance().getWrittenDate(), 0L, 0L, false, false);
        else return this.mainTitleVO;
    }
}
