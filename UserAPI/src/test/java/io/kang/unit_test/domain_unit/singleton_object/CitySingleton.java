package io.kang.unit_test.domain_unit.singleton_object;

import io.kang.domain.City;

public enum CitySingleton {
    INSTANCE;
    private City city = new City(1L, "CITY01");
    public City getInstance(){
        if(city==null)
            return new City(1L, "CITY01");
        else return city;
    }
}
