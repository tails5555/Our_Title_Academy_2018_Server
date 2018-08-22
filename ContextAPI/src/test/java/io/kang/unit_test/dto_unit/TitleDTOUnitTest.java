package io.kang.unit_test.dto_unit;

import io.kang.domain.mysql.Title;
import io.kang.dto.mysql.TitleDTO;
import io.kang.unit_test.testing_singleton.domain_unit.TitleUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

public class TitleDTOUnitTest {
    private static final Title title = TitleUpdateSingleton.INSTANCE.getInstance();
    private static final TitleDTO titleDTO = TitleDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test(){
        TitleDTO titleDTO = TitleDTO.builtToDTO(this.title);
        Assert.assertEquals(titleDTO, this.titleDTO);
    }

    @Test
    public void built_to_domain_test(){
        Title title = TitleDTO.builtToDomain(this.titleDTO);
        Assert.assertEquals(title, this.title);
    }
}
