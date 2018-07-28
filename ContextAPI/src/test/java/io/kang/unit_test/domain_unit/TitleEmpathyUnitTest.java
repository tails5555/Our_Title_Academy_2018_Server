package io.kang.unit_test.domain_unit;

import io.kang.domain.Title;
import io.kang.domain.empathy.TitleEmpathy;
import io.kang.enumeration.Status;
import io.kang.unit_test.testing_singleton.domain_unit.TitleUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TitleEmpathyUnitTest {
    private static final long ID = 1L;
    private static final Status STATUS = Status.LIKE;
    private static final LocalDateTime CHECKED_DATE = LocalDateTime.MIN;
    private static final String USER_ID = "EMPATHY_USER_ID01";
    private static final Title TITLE = TitleUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void id_getter_and_setter_test(){
        TitleEmpathy titleEmpathy = new TitleEmpathy();
        titleEmpathy.setId(ID);
        long id = titleEmpathy.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void status_getter_and_setter_test(){
        TitleEmpathy titleEmpathy = new TitleEmpathy();
        titleEmpathy.setStatus(STATUS);
        Status status = titleEmpathy.getStatus();
        Assert.assertEquals(status, STATUS);
    }

    @Test
    public void checked_date_getter_and_setter_test(){
        TitleEmpathy titleEmpathy = new TitleEmpathy();
        titleEmpathy.setCheckedDate(CHECKED_DATE);
        LocalDateTime checkedDate = titleEmpathy.getCheckedDate();
        Assert.assertEquals(checkedDate, CHECKED_DATE);
    }

    @Test
    public void user_id_getter_and_setter_test(){
        TitleEmpathy titleEmpathy = new TitleEmpathy();
        titleEmpathy.setUserId(USER_ID);
        String userId = titleEmpathy.getUserId();
        Assert.assertEquals(userId, USER_ID);
    }

    @Test
    public void title_getter_and_setter_test(){
        TitleEmpathy titleEmpathy = new TitleEmpathy();
        titleEmpathy.setTitle(TITLE);
        Title title = titleEmpathy.getTitle();
        Assert.assertEquals(title, TITLE);
    }

    @Test
    public void equals_test(){
        TitleEmpathy titleEmpathy = new TitleEmpathy();
        titleEmpathy.setId(ID);
        titleEmpathy.setStatus(STATUS);
        titleEmpathy.setCheckedDate(CHECKED_DATE);
        titleEmpathy.setUserId(USER_ID);
        titleEmpathy.setTitle(TITLE);

        TitleEmpathy sameTitleEmpathy = new TitleEmpathy(ID, STATUS, CHECKED_DATE, USER_ID, TITLE);
        Assert.assertEquals(titleEmpathy, sameTitleEmpathy);
    }

    @Test
    public void to_string_test() {
        TitleEmpathy titleEmpathy = new TitleEmpathy();
        titleEmpathy.setId(ID);
        titleEmpathy.setStatus(STATUS);
        titleEmpathy.setCheckedDate(CHECKED_DATE);
        titleEmpathy.setUserId(USER_ID);
        titleEmpathy.setTitle(TITLE);

        String string = titleEmpathy.toString();
        Assert.assertEquals("TitleEmpathy(userId=EMPATHY_USER_ID01, title=Title(id=1, request=Request(id=1, category=Category(id=1, name=CATEGORY_NAME01), userId=REQUEST_USER_ID01, intro=REQUEST_INTRO_01, context=REQUEST_CONTEXT_01, available=false, writtenDate=-999999999-01-01T00:00, view=0), userId=TITLE_USER_ID01, context=TITLE_CONTEXT_01, writtenDate=-999999999-01-01T00:00))", string);
    }
}
