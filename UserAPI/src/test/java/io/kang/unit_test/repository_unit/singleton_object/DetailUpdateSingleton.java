package io.kang.unit_test.repository_unit.singleton_object;

import io.kang.domain.Age;
import io.kang.domain.City;
import io.kang.domain.Detail;
import io.kang.domain.User;

public enum DetailUpdateSingleton {
    INSTANCE;
    private Age age = AgeUpdateSingleton.INSTANCE.getInstance();
    private City city = CityUpdateSingleton.INSTANCE.getInstance();
    private User user = UserUpdateSingleton.INSTANCE.getInstance();
    private Detail detail = new Detail(1L, user, "NAME1", "DETAIL_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", city, age);
    public Detail getInstance(){
        if(detail == null){
            return new Detail(1L, user, "NAME1", "DETAIL_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", city, age);
        }
        else return detail;
    }
}
