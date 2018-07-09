package io.kang.unit_test.singleton_object.value_object_testing;

import io.kang.vo.CityVO;

public enum CityVOSingleton {
    INSTANCE;
    private CityVO cityVO = new CityVO(1L, "CITY_NAME01");
    public CityVO getInstance(){
        if(this.cityVO == null)
            return new CityVO(1L, "CITY_NAME01");
        else return this.cityVO;
    }
}
