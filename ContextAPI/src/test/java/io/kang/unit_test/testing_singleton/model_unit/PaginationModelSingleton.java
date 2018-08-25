package io.kang.unit_test.testing_singleton.model_unit;

import io.kang.model.PaginationModel;

public enum PaginationModelSingleton {
    INSTANCE;
    private PaginationModel paginationModel = new PaginationModel(1L, 6, 1, 0, 0, 1L, "");
    public PaginationModel getInstance(){
        if(this.paginationModel == null)
            return new PaginationModel(1L, 6, 1, 0, 0, 1L, "");
        else return this.paginationModel;
    }
}
