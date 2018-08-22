package io.kang.unit_test.dto_unit;

import io.kang.domain.mysql.TitleEmpathy;
import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.unit_test.testing_singleton.domain_unit.TitleEmpathyUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleEmpathyDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

public class TitleEmpathyDTOUnitTest {
    private TitleEmpathy titleEmpathy = TitleEmpathyUpdateSingleton.INSTANCE.getInstance();
    private TitleEmpathyDTO titleEmpathyDTO = TitleEmpathyDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test() {
        TitleEmpathyDTO titleEmpathyDTO = TitleEmpathyDTO.builtToDTO(this.titleEmpathy);
        Assert.assertEquals(titleEmpathyDTO, this.titleEmpathyDTO);
    }

    @Test
    public void built_to_domain_test(){
        TitleEmpathy titleEmpathy = TitleEmpathyDTO.builtToDomain(this.titleEmpathyDTO);
        Assert.assertEquals(titleEmpathy, this.titleEmpathy);
    }
}
