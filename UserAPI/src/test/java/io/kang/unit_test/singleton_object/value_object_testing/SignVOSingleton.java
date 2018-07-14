package io.kang.unit_test.singleton_object.value_object_testing;

public enum SignVOSingleton {
    INSTANCE;
    private SignVO signVO = new SignVO("LOGIN_ID01", "SIGN_MAIN_PASSWORD01", "SIGN_SUB_PASSWORD01", "NAME01", "SIGN_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", 1L, 1L);
    public SignVO getInstance(){
        if(this.signVO == null)
            return new SignVO("LOGIN_ID01", "SIGN_MAIN_PASSWORD01", "SIGN_SUB_PASSWORD01", "NAME01", "SIGN_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", 1L, 1L);
        else return this.signVO;
    }
}
