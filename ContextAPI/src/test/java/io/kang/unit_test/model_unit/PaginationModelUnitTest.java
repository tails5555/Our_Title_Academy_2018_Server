package io.kang.unit_test.model_unit;

import io.kang.model.PaginationModel;
import io.kang.unit_test.testing_singleton.model_unit.PaginationModelSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PaginationModelUnitTest {
    @Test
    public void get_query_string_test() throws UnsupportedEncodingException {
        PaginationModel paginationModel = PaginationModelSingleton.INSTANCE.getInstance();

        String temp = (paginationModel.getSt() == null) ? "" : URLEncoder.encode(paginationModel.getSt(), "UTF-8");
        String query = String.format("id=%d&pg=%d&sz=%d&ob=%d&sb=%d&st=%s", paginationModel.getId(), paginationModel.getPg(), paginationModel.getSz(), paginationModel.getOb(), paginationModel.getSb(), temp);
        Assert.assertEquals(paginationModel.getQueryString(), query);
    }
}
