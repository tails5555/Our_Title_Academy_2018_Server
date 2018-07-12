package io.kang.unit_test.singleton_object.service_testing;

import io.kang.model.CityModel;

public enum CityModelCreateSingleton {
    INSTANCE;
    private CityModel cityModel = new CityModel(null, "CITY_NAME01");
    public CityModel getInstance(){
        if(this.cityModel == null)
            return new CityModel(null, "CITY_NAME01");
        return this.cityModel;
    }
}
