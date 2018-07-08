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
    private Detail detail = new Detail(1L, this.user, "NAME1", "DETAIL_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", this.city, this.age);
    public Detail getInstance(){
        if(this.age == null){
            this.age = AgeUpdateSingleton.INSTANCE.getInstance();
        }
        if(this.city == null){
            this.city = CityUpdateSingleton.INSTANCE.getInstance();
        }
        if(this.user == null){
            this.user = UserUpdateSingleton.INSTANCE.getInstance();
        }
        if(this.detail == null){
            return new Detail(1L, this.user, "NAME1", "DETAIL_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", this.city, this.age);
        }
        else return this.detail;
    }
}
