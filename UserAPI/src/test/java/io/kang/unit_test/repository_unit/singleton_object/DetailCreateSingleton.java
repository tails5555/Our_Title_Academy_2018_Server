package io.kang.unit_test.repository_unit.singleton_object;

import io.kang.domain.Age;
import io.kang.domain.City;
import io.kang.domain.Detail;
import io.kang.domain.User;

public enum DetailCreateSingleton {
    INSTANCE;
    private Age age = AgeUpdateSingleton.INSTANCE.getInstance();
    private City city = CityUpdateSingleton.INSTANCE.getInstance();
    private User user = UserUpdateSingleton.INSTANCE.getInstance();
    private Detail detail = new Detail(null, user, "NAME0", "DETAIL_EMAIL00", "HOME_NUMBER00", "PHONE_NUMBER00", city, age);
    public Detail getInstance(){
        if(detail == null){
            return new Detail(null, user, "NAME0", "DETAIL_EMAIL00", "HOME_NUMBER00", "PHONE_NUMBER00", city, age);
        }
        else return detail;
    }
}
