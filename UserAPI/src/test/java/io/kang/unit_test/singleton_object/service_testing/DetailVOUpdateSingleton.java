package io.kang.unit_test.singleton_object.service_testing;

import io.kang.unit_test.singleton_object.value_object_testing.AgeVOSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.CityVOSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.UserVOSingleton;
import io.kang.vo.DetailVO;

public enum DetailVOUpdateSingleton {
    INSTANCE;
    private DetailVO detailVO = new DetailVO(1L, UserVOSingleton.INSTANCE.getInstance(), "NAME1", "DETAIL_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", CityVOSingleton.INSTANCE.getInstance(), AgeVOSingleton.INSTANCE.getInstance());
    public DetailVO getInstance(){
        if(this.detailVO == null)
            return new DetailVO(1L, UserVOSingleton.INSTANCE.getInstance(), "NAME1", "DETAIL_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", CityVOSingleton.INSTANCE.getInstance(), AgeVOSingleton.INSTANCE.getInstance());
        else return this.detailVO;
    }
}
