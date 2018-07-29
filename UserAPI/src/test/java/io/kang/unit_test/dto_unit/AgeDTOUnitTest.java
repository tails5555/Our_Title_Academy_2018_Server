package io.kang.unit_test.dto_unit;

import io.kang.domain.Age;
import io.kang.dto.AgeDTO;
import io.kang.unit_test.singleton_object.domain_unit.AgeUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.AgeDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AgeDTOUnitTest {
    private static final Age age = AgeUpdateSingleton.INSTANCE.getInstance();
    private static final AgeDTO ageDTO = AgeDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test() throws IOException {
        AgeDTO ageDTO = AgeDTO.builtToDTO(this.age);
        Assert.assertEquals(ageDTO, this.ageDTO);
    }

    @Test
    public void built_to_domain_test() throws IOException {
        Age age = AgeDTO.builtToDomain(this.ageDTO);
        Assert.assertEquals(age, this.age);
    }
}
