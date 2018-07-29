package io.kang.unit_test.singleton_object.dto_unit;

import io.kang.dto.CityDTO;

public enum CityDTOUpdateSingleton {
    INSTANCE;
    private CityDTO cityDTO = new CityDTO(1L, "CITY_NAME01");
    public CityDTO getInstance(){
        if(this.cityDTO == null)
            return new CityDTO(1L, "CITY_NAME01");
        return this.cityDTO;
    }
}
