package io.kang.unit_test.domain_unit;

import io.kang.domain.mysql.Request;
import io.kang.domain.mysql.Title;
import io.kang.unit_test.testing_singleton.domain_unit.RequestUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TitleUnitTest {
    private static final long ID = 1L;
    private static final Request REQUEST = RequestUpdateSingleton.INSTANCE.getInstance();
    private static final String USER_ID = "TITLE_USER_ID01";
    private static final String CONTEXT = "TITLE_CONTEXT_01";
    private static final LocalDateTime WRITTEN_DATE = LocalDateTime.MIN;

    @Test
    public void id_getter_and_setter_test(){
        Title title = new Title();
        title.setId(ID);
        long id = title.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void request_getter_and_setter_test(){
        Title title = new Title();
        title.setRequest(REQUEST);
        Request request = title.getRequest();
        Assert.assertEquals(request, REQUEST);
    }

    @Test
    public void user_id_getter_and_setter_test(){
        Title title = new Title();
        title.setUserId(USER_ID);
        String userId = title.getUserId();
        Assert.assertEquals(userId, USER_ID);
    }

    @Test
    public void context_getter_and_setter_test(){
        Title title = new Title();
        title.setContext(CONTEXT);
        String context = title.getContext();
        Assert.assertEquals(context, CONTEXT);
    }

    @Test
    public void written_date_getter_and_setter_test(){
        Title title = new Title();
        title.setWrittenDate(WRITTEN_DATE);
        LocalDateTime writtenDate = title.getWrittenDate();
        Assert.assertEquals(writtenDate, WRITTEN_DATE);
    }

    @Test
    public void equals_test(){
        Title title = new Title();
        title.setId(ID);
        title.setRequest(REQUEST);
        title.setUserId(USER_ID);
        title.setContext(CONTEXT);
        title.setWrittenDate(WRITTEN_DATE);

        Title sameTitle = new Title(ID, REQUEST, USER_ID, CONTEXT, WRITTEN_DATE);
        Assert.assertEquals(title, sameTitle);
    }

    @Test
    public void to_string_test(){
        Title title = new Title();
        title.setId(ID);
        title.setRequest(REQUEST);
        title.setUserId(USER_ID);
        title.setContext(CONTEXT);
        title.setWrittenDate(WRITTEN_DATE);

        String string = title.toString();
        Assert.assertEquals("Title(id=1, request=Request(id=1, category=Category(id=1, name=CATEGORY_NAME01), userId=REQUEST_USER_ID01, intro=REQUEST_INTRO_01, context=REQUEST_CONTEXT_01, available=false, writtenDate=-999999999-01-01T00:00, view=0), userId=TITLE_USER_ID01, context=TITLE_CONTEXT_01, writtenDate=-999999999-01-01T00:00)", string);
    }
}
