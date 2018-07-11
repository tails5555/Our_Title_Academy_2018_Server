package io.kang.unit_test.singleton_object.model_testing;

import io.kang.model.CityModel;

public enum CityModelSingleton {
    INSTANCE;
    private CityModel cityModel = new CityModel(1L, "CITY_NAME01");
    public CityModel getInstance(){
        if(this.cityModel == null)
            return new CityModel(1L, "CITY_NAME01");
        else return this.cityModel;
    }
}
