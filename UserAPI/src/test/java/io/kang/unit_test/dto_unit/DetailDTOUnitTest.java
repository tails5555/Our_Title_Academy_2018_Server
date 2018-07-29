package io.kang.unit_test.dto_unit;

import io.kang.domain.Detail;
import io.kang.dto.DetailDTO;
import io.kang.unit_test.singleton_object.domain_unit.DetailUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.DetailDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DetailDTOUnitTest {
    private static Detail detail = DetailUpdateSingleton.INSTANCE.getInstance();
    private static DetailDTO detailDTO = DetailDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test() throws IOException {
        DetailDTO detailDTO = DetailDTO.builtToDTO(this.detail);
        Assert.assertEquals(detailDTO, this.detailDTO);
    }

    @Test
    public void built_to_domain_test() throws IOException {
        Detail detail = DetailDTO.builtToDomain(this.detailDTO);
        Assert.assertEquals(detail, this.detail);
    }
}
