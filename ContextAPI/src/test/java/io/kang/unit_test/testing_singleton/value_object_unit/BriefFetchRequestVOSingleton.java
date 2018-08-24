package io.kang.unit_test.testing_singleton.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleDTOUpdateSingleton;
import io.kang.vo.BriefFetchRequestVO;

public enum BriefFetchRequestVOSingleton {
    INSTANCE;
    private BriefFetchRequestVO briefFetchRequestVO = new BriefFetchRequestVO(RequestDTOUpdateSingleton.INSTANCE.getInstance().getId(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getUserId(), (RequestDTOUpdateSingleton.INSTANCE.getInstance().getCategory() != null) ? RequestDTOUpdateSingleton.INSTANCE.getInstance().getCategory().getId() : -1L, (RequestDTOUpdateSingleton.INSTANCE.getInstance().getCategory() != null) ? RequestDTOUpdateSingleton.INSTANCE.getInstance().getCategory().getName() : "", TitleDTOUpdateSingleton.INSTANCE.getInstance().getContext(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getIntro(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getContext(), 0L, 0L, 0L, RequestDTOUpdateSingleton.INSTANCE.getInstance().getWrittenDate());
    public BriefFetchRequestVO getInstance(){
        if(this.briefFetchRequestVO == null)
            return new BriefFetchRequestVO(RequestDTOUpdateSingleton.INSTANCE.getInstance().getId(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getUserId(), (RequestDTOUpdateSingleton.INSTANCE.getInstance().getCategory() != null) ? RequestDTOUpdateSingleton.INSTANCE.getInstance().getCategory().getId() : -1L, (RequestDTOUpdateSingleton.INSTANCE.getInstance().getCategory() != null) ? RequestDTOUpdateSingleton.INSTANCE.getInstance().getCategory().getName() : "", TitleDTOUpdateSingleton.INSTANCE.getInstance().getContext(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getIntro(), RequestDTOUpdateSingleton.INSTANCE.getInstance().getContext(), 0L, 0L, 0L, RequestDTOUpdateSingleton.INSTANCE.getInstance().getWrittenDate());
        else return this.briefFetchRequestVO;
    }
}
