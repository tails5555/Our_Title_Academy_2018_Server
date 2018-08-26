package io.kang.unit_test.model_unit;

import io.kang.dto.mysql.TitleDTO;
import io.kang.model.TitleModel;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleDTOCreateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.model_unit.TitleModelSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TitleModelUnitTest {
    @Test
    public void built_to_dto_test(){
        TitleDTO titleDTO = TitleModel.builtToDTO(RequestDTOUpdateSingleton.INSTANCE.getInstance(), TitleModelSingleton.INSTANCE.getInstance());
        titleDTO.setId(null);
        titleDTO.setWrittenDate(LocalDateTime.MIN);
        Assert.assertEquals(titleDTO, TitleDTOCreateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void built_to_dto_is_existed_test(){
        TitleDTO titleDTO = TitleModel.builtToDTOIsExisted(1L, RequestDTOUpdateSingleton.INSTANCE.getInstance(), TitleModelSingleton.INSTANCE.getInstance(), LocalDateTime.MIN);
        Assert.assertEquals(titleDTO, TitleDTOUpdateSingleton.INSTANCE.getInstance());

    }
}
