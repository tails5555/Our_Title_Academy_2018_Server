package io.kang.unit_test.singleton_object.dto_unit;

import io.kang.dto.DetailDTO;

public enum DetailDTOCreateSingleton {
    INSTANCE;
    private DetailDTO detailDTO = new DetailDTO(null, UserDTOUpdateSingleton.INSTANCE.getInstance(), "NAME1", "DETAIL_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", CityDTOUpdateSingleton.INSTANCE.getInstance(), AgeDTOUpdateSingleton.INSTANCE.getInstance());
    public DetailDTO getInstance(){
        if(this.detailDTO == null)
            return new DetailDTO(null, UserDTOUpdateSingleton.INSTANCE.getInstance(), "NAME1", "DETAIL_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", CityDTOUpdateSingleton.INSTANCE.getInstance(), AgeDTOUpdateSingleton.INSTANCE.getInstance());
        else return this.detailDTO;
    }
}
