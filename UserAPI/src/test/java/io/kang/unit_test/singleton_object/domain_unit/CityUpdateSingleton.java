package io.kang.unit_test.singleton_object.domain_unit;

import io.kang.domain.City;

public enum CityUpdateSingleton {
    INSTANCE;
    private City city = new City(1L, "CITY_NAME01");
    public City getInstance(){
        if(this.city == null)
            return new City(1L, "CITY_NAME01");
        else return this.city;
    }
}
