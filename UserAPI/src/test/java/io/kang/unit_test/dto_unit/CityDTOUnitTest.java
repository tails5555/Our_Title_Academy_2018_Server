package io.kang.unit_test.dto_unit;

import io.kang.domain.City;
import io.kang.dto.CityDTO;
import io.kang.unit_test.singleton_object.domain_unit.CityUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.CityDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CityDTOUnitTest {
    private static City city = CityUpdateSingleton.INSTANCE.getInstance();
    private static CityDTO cityDTO = CityDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test() throws IOException {
        CityDTO cityDTO = CityDTO.builtToDTO(this.city);
        Assert.assertEquals(cityDTO, this.cityDTO);
    }

    @Test
    public void built_to_domain_test() throws IOException {
        City city = CityDTO.builtToDomain(this.cityDTO);
        Assert.assertEquals(city, this.city);
    }
}
