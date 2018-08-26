package io.kang.unit_test.model_unit;

import io.kang.dto.mysql.RequestDTO;
import io.kang.model.RequestModel;
import io.kang.unit_test.testing_singleton.dto_unit.CategoryDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOCreateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.model_unit.RequestModelSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class RequestModelUnitTest {
    @Test
    public void built_to_dto_test(){
        RequestDTO requestDTO = RequestModel.builtToDTO(RequestModelSingleton.INSTANCE.getInstance());
        requestDTO.setWrittenDate(LocalDateTime.MIN);
        Assert.assertEquals(requestDTO, RequestDTOCreateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void built_to_dto_existed_test(){
        RequestDTO requestDTO = RequestModel.builtToDTOIsExisted(RequestDTOUpdateSingleton.INSTANCE.getInstance(), CategoryDTOUpdateSingleton.INSTANCE.getInstance(), RequestModelSingleton.INSTANCE.getInstance());
        requestDTO.setWrittenDate(LocalDateTime.MIN);
        Assert.assertEquals(requestDTO, RequestDTOUpdateSingleton.INSTANCE.getInstance());
    }
}
