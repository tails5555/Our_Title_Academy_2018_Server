package io.kang.unit_test.singleton_object.repository_testing;

import io.kang.domain.Age;
import io.kang.domain.City;
import io.kang.domain.Detail;
import io.kang.domain.User;

public enum DetailCreateSingleton {
    INSTANCE;
    private Age age = AgeUpdateSingleton.INSTANCE.getInstance();
    private City city = CityUpdateSingleton.INSTANCE.getInstance();
    private User user = UserUpdateSingleton.INSTANCE.getInstance();
    private Detail detail = new Detail(null, this.user, "NAME0", "DETAIL_EMAIL00", "HOME_NUMBER00", "PHONE_NUMBER00", this.city, this.age);
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
            return new Detail(null, this.user, "NAME0", "DETAIL_EMAIL00", "HOME_NUMBER00", "PHONE_NUMBER00", this.city, this.age);
        }
        else return this.detail;
    }
}
