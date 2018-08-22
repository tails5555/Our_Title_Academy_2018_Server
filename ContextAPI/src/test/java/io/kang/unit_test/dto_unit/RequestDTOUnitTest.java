package io.kang.unit_test.dto_unit;

import io.kang.domain.mysql.Request;
import io.kang.dto.mysql.RequestDTO;
import io.kang.unit_test.testing_singleton.domain_unit.RequestUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

public class RequestDTOUnitTest {
    private static final Request request = RequestUpdateSingleton.INSTANCE.getInstance();
    private static final RequestDTO requestDTO = RequestDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test(){
        RequestDTO requestDTO = RequestDTO.builtToDTO(this.request);
        Assert.assertEquals(requestDTO, this.requestDTO);
    }

    @Test
    public void built_to_domain_test(){
        Request request = RequestDTO.builtToDomain(this.requestDTO);
        Assert.assertEquals(request, this.request);
    }
}
