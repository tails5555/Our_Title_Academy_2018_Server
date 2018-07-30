package io.kang.unit_test.domain_unit;

import io.kang.domain.redis.TodayRequest;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TodayRequestUnitTest {
    private static final long ID = 1L;
    private static final long REQUEST_ID = 1L;
    private static final LocalDateTime SELECT_DATE = LocalDateTime.MIN;

    @Test
    public void id_getter_and_setter_test(){
        TodayRequest todayRequest = new TodayRequest();
        todayRequest.setId(ID);
        long id = todayRequest.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void request_id_getter_and_setter_test(){
        TodayRequest todayRequest = new TodayRequest();
        todayRequest.setRequestId(REQUEST_ID);
        long requestId = todayRequest.getRequestId();
        Assert.assertEquals(requestId, REQUEST_ID);
    }

    @Test
    public void select_date_getter_and_setter_test(){
        TodayRequest todayRequest = new TodayRequest();
        todayRequest.setSelectDate(SELECT_DATE);
        LocalDateTime selectDate = todayRequest.getSelectDate();
        Assert.assertEquals(selectDate, SELECT_DATE);
    }

    @Test
    public void equals_test(){
        TodayRequest todayRequest = new TodayRequest();
        todayRequest.setId(ID);
        todayRequest.setRequestId(REQUEST_ID);
        todayRequest.setSelectDate(SELECT_DATE);

        TodayRequest sameTodayRequest = new TodayRequest(ID, REQUEST_ID, SELECT_DATE);
        Assert.assertEquals(todayRequest, sameTodayRequest);
    }

    @Test
    public void to_string_test(){
        TodayRequest todayRequest = new TodayRequest();
        todayRequest.setId(ID);
        todayRequest.setRequestId(REQUEST_ID);
        todayRequest.setSelectDate(SELECT_DATE);

        String string = todayRequest.toString();
        Assert.assertEquals("TodayRequest(id=1, requestId=1, selectDate=-999999999-01-01T00:00)", string);
    }
}
