package io.kang.unit_test.domain_unit;

import io.kang.domain.Request;
import io.kang.domain.Request;
import io.kang.domain.empathy.CommentEmpathy;
import io.kang.domain.empathy.RequestEmpathy;
import io.kang.enumeration.Status;
import io.kang.unit_test.testing_singleton.domain_unit.CommentUpdateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.RequestUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class RequestEmpathyUnitTest {
    private static final long ID = 1L;
    private static final Status STATUS = Status.LIKE;
    private static final LocalDateTime CHECKED_DATE = LocalDateTime.MIN;
    private static final String USER_ID = "EMPATHY_USER_ID01";
    private static final Request REQUEST = RequestUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void id_getter_and_setter_test(){
        RequestEmpathy requestEmpathy = new RequestEmpathy();
        requestEmpathy.setId(ID);
        long id = requestEmpathy.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void status_getter_and_setter_test(){
        RequestEmpathy requestEmpathy = new RequestEmpathy();
        requestEmpathy.setStatus(STATUS);
        Status status = requestEmpathy.getStatus();
        Assert.assertEquals(status, STATUS);
    }

    @Test
    public void checked_date_getter_and_setter_test(){
        RequestEmpathy requestEmpathy = new RequestEmpathy();
        requestEmpathy.setCheckedDate(CHECKED_DATE);
        LocalDateTime checkedDate = requestEmpathy.getCheckedDate();
        Assert.assertEquals(checkedDate, CHECKED_DATE);
    }

    @Test
    public void user_id_getter_and_setter_test(){
        RequestEmpathy requestEmpathy = new RequestEmpathy();
        requestEmpathy.setUserId(USER_ID);
        String userId = requestEmpathy.getUserId();
        Assert.assertEquals(userId, USER_ID);
    }

    @Test
    public void comment_getter_and_setter_test(){
        RequestEmpathy requestEmpathy = new RequestEmpathy();
        requestEmpathy.setRequest(REQUEST);
        Request request = requestEmpathy.getRequest();
        Assert.assertEquals(request, REQUEST);
    }

    @Test
    public void equals_test(){
        RequestEmpathy requestEmpathy = new RequestEmpathy();
        requestEmpathy.setId(ID);
        requestEmpathy.setStatus(STATUS);
        requestEmpathy.setCheckedDate(CHECKED_DATE);
        requestEmpathy.setUserId(USER_ID);
        requestEmpathy.setRequest(REQUEST);

        RequestEmpathy sameRequestEmpathy = new RequestEmpathy(ID, STATUS, CHECKED_DATE, USER_ID, REQUEST);
        Assert.assertEquals(requestEmpathy, sameRequestEmpathy);
    }

    @Test
    public void to_string_test() {
        RequestEmpathy requestEmpathy = new RequestEmpathy();
        requestEmpathy.setId(ID);
        requestEmpathy.setStatus(STATUS);
        requestEmpathy.setCheckedDate(CHECKED_DATE);
        requestEmpathy.setUserId(USER_ID);
        requestEmpathy.setRequest(REQUEST);

        String string = requestEmpathy.toString();
        Assert.assertEquals("RequestEmpathy(userId=EMPATHY_USER_ID01, request=Request(id=1, category=Category(id=1, name=CATEGORY_NAME01), userId=REQUEST_USER_ID01, intro=REQUEST_INTRO_01, context=REQUEST_CONTEXT_01, available=false, writtenDate=-999999999-01-01T00:00, view=0))", string);
    }
}
