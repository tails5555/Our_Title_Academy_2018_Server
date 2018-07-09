package io.kang.unit_test.singleton_object.repository_testing;

import io.kang.domain.City;

public enum CityCreateSingleton {
    INSTANCE;
    private City city = new City(null, "CITY_NAME00");
    public City getInstance(){
        if(this.city == null)
            return new City(null, "CITY_NAME00");
        else return this.city;
    }
}
