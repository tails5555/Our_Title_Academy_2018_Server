package io.kang.unit_test.singleton_object.domain_unit;

import io.kang.domain.City;

public enum CityCreateSingleton {
    INSTANCE;
    private City city = new City(null, "CITY_NAME01");
    public City getInstance(){
        if(this.city == null)
            return new City(null, "CITY_NAME01");
        else return this.city;
    }
}
