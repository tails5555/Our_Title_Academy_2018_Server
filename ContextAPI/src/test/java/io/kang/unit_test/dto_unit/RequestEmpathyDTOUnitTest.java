package io.kang.unit_test.dto_unit;

import io.kang.domain.mysql.RequestEmpathy;
import io.kang.dto.mysql.RequestEmpathyDTO;
import io.kang.unit_test.testing_singleton.domain_unit.RequestEmpathyUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestEmpathyDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

public class RequestEmpathyDTOUnitTest {
    private RequestEmpathy requestEmpathy = RequestEmpathyUpdateSingleton.INSTANCE.getInstance();
    private RequestEmpathyDTO requestEmpathyDTO = RequestEmpathyDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test(){
        RequestEmpathyDTO requestEmpathyDTO = RequestEmpathyDTO.builtToDTO(this.requestEmpathy);
        Assert.assertEquals(requestEmpathyDTO, this.requestEmpathyDTO);
    }

    @Test
    public void built_to_domain_test(){
        RequestEmpathy requestEmpathy = RequestEmpathyDTO.builtToDomain(this.requestEmpathyDTO);
        Assert.assertEquals(requestEmpathy, this.requestEmpathy);
    }
}
