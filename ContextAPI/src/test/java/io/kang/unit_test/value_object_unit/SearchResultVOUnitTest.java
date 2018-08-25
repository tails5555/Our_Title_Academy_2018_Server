package io.kang.unit_test.value_object_unit;

import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.SearchResultVORequestSingleton;
import io.kang.unit_test.testing_singleton.value_object_unit.SearchResultVOTitleSingleton;
import io.kang.vo.SearchResultVO;
import org.junit.Assert;
import org.junit.Test;

public class SearchResultVOUnitTest {
    @Test
    public void built_to_vo_with_request_dto_test(){
        RequestDTO requestDTO = RequestDTOUpdateSingleton.INSTANCE.getInstance();
        requestDTO.setAvailable(true);
        SearchResultVO searchResultVO = SearchResultVO.builtToVOWithRequestDTO(requestDTO, 0L, 0L);
        Assert.assertEquals(searchResultVO, SearchResultVORequestSingleton.INSTANCE.getInstance());
    }

    @Test
    public void built_to_vo_with_title_dto_test(){
        TitleDTO titleDTO = TitleDTOUpdateSingleton.INSTANCE.getInstance();
        titleDTO.getRequest().setAvailable(true);
        SearchResultVO searchResultVO = SearchResultVO.builtToVOWithTitleDTO(TitleDTOUpdateSingleton.INSTANCE.getInstance(), 0L, 0L);
        Assert.assertEquals(searchResultVO, SearchResultVOTitleSingleton.INSTANCE.getInstance());
    }
}
