package io.kang.unit_test.singleton_object.dto_unit;

import io.kang.dto.CityDTO;

public enum CityDTOCreateSingleton {
    INSTANCE;
    private CityDTO cityDTO = new CityDTO(null, "CITY_NAME01");
    public CityDTO getInstance(){
        if(this.cityDTO == null)
            return new CityDTO(null, "CITY_NAME01");
        return this.cityDTO;
    }
}
