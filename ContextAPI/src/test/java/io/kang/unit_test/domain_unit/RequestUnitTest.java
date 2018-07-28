package io.kang.unit_test.domain_unit;

import io.kang.domain.Category;
import io.kang.domain.Request;
import io.kang.unit_test.testing_singleton.domain_unit.CategoryUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class RequestUnitTest {
    private static final long ID = 1L;
    private static final Category CATEGORY = CategoryUpdateSingleton.INSTANCE.getInstance();
    private static final String USER_ID = "REQUEST_USER_ID01";
    private static final String INTRO = "REQUEST_INTRO_01";
    private static final String CONTEXT = "REQUEST_CONTEXT_01";
    private static final boolean AVAILABLE = false;
    private static final LocalDateTime WRITTEN_DATE = LocalDateTime.MIN;
    private static final int VIEW = 0;

    @Test
    public void id_getter_and_setter_test(){
        Request request = new Request();
        request.setId(ID);
        long id = request.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void category_getter_and_setter_test(){
        Request request = new Request();
        request.setCategory(CATEGORY);
        Category category = request.getCategory();
        Assert.assertEquals(category, CATEGORY);
    }

    @Test
    public void user_id_getter_and_setter_test(){
        Request request = new Request();
        request.setUserId(USER_ID);
        String userId = request.getUserId();
        Assert.assertEquals(userId, USER_ID);
    }

    @Test
    public void intro_getter_and_setter_test(){
        Request request = new Request();
        request.setIntro(INTRO);
        String intro = request.getIntro();
        Assert.assertEquals(intro, INTRO);
    }

    @Test
    public void context_getter_and_setter_test(){
        Request request = new Request();
        request.setContext(CONTEXT);
        String context = request.getContext();
        Assert.assertEquals(context, CONTEXT);
    }

    @Test
    public void available_getter_and_setter_test(){
        Request request = new Request();
        request.setAvailable(AVAILABLE);
        boolean available = request.getAvailable();
        Assert.assertEquals(available, AVAILABLE);
    }


    @Test
    public void written_date_getter_and_setter_test(){
        Request request = new Request();
        request.setWrittenDate(WRITTEN_DATE);
        LocalDateTime writtenDate = request.getWrittenDate();
        Assert.assertEquals(writtenDate, WRITTEN_DATE);
    }

    @Test
    public void view_getter_and_setter_test(){
        Request request = new Request();
        request.setView(VIEW);
        int view = request.getView();
        Assert.assertEquals(view, VIEW);
    }

    @Test
    public void equals_test(){
        Request request = new Request();
        request.setId(ID);
        request.setCategory(CATEGORY);
        request.setUserId(USER_ID);
        request.setIntro(INTRO);
        request.setContext(CONTEXT);
        request.setAvailable(AVAILABLE);
        request.setWrittenDate(WRITTEN_DATE);
        request.setView(VIEW);

        Request sameRequest = new Request(ID, CATEGORY, USER_ID, INTRO, CONTEXT, AVAILABLE, WRITTEN_DATE, VIEW);
        Assert.assertEquals(request, sameRequest);
    }

    @Test
    public void to_string_test(){
        Request request = new Request();
        request.setId(ID);
        request.setCategory(CATEGORY);
        request.setUserId(USER_ID);
        request.setIntro(INTRO);
        request.setContext(CONTEXT);
        request.setAvailable(AVAILABLE);
        request.setWrittenDate(WRITTEN_DATE);
        request.setView(VIEW);

        String string = request.toString();
        Assert.assertEquals("Request(id=1, category=Category(id=1, name=CATEGORY_NAME01), userId=REQUEST_USER_ID01, intro=REQUEST_INTRO_01, context=REQUEST_CONTEXT_01, available=false, writtenDate=-999999999-01-01T00:00, view=0)", string);
    }
}
