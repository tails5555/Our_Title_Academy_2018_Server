package io.kang.unit_test.repository_unit.singleton_object;

import io.kang.domain.City;

public enum CityCreateSingleton {
    INSTANCE;
    private City city = new City(null, "CITY_NAME00");
    public City getInstance(){
        if(city == null)
            return new City(null, "CITY_NAME00");
        else return city;
    }
}
