package io.kang.unit_test.singleton_object.service_testing;

import io.kang.model.CityModel;

public enum CityModelUpdateSingleton {
    INSTANCE;
    private CityModel cityModel = new CityModel(1L, "CITY_NAME01");
    public CityModel getInstance(){
        if(this.cityModel == null)
            return new CityModel(1L, "CITY_NAME01");
        else return this.cityModel;
    }
}
