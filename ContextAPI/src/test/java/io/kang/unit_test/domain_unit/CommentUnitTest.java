package io.kang.unit_test.domain_unit;

import io.kang.domain.Comment;
import io.kang.domain.Request;
import io.kang.unit_test.testing_singleton.domain_unit.RequestUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class CommentUnitTest {
    private static final long ID = 1L;
    private static final String USER_ID = "COMMENT_USER_ID01";
    private static final Request REQUEST = RequestUpdateSingleton.INSTANCE.getInstance();
    private static final String CONTEXT = "COMMENT_CONTEXT01";
    private static final LocalDateTime WRITTEN_DATE = LocalDateTime.MIN;

    @Test
    public void id_getter_and_setter_test(){
        Comment comment = new Comment();
        comment.setId(ID);
        long id = comment.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void user_id_getter_and_setter_test(){
        Comment comment = new Comment();
        comment.setUserId(USER_ID);
        String userId = comment.getUserId();
        Assert.assertEquals(userId, USER_ID);
    }

    @Test
    public void request_getter_and_setter_test(){
        Comment comment = new Comment();
        comment.setRequest(REQUEST);
        Request request = comment.getRequest();
        Assert.assertEquals(request, REQUEST);
    }

    @Test
    public void context_getter_and_setter_test(){
        Comment comment = new Comment();
        comment.setContext(CONTEXT);
        String context = comment.getContext();
        Assert.assertEquals(context, CONTEXT);
    }

    @Test
    public void written_date_getter_and_setter_test(){
        Comment comment = new Comment();
        comment.setWrittenDate(WRITTEN_DATE);
        LocalDateTime writtenDate = comment.getWrittenDate();
        Assert.assertEquals(writtenDate, WRITTEN_DATE);
    }

    @Test
    public void equals_test(){
        Comment comment = new Comment();
        comment.setId(ID);
        comment.setUserId(USER_ID);
        comment.setRequest(REQUEST);
        comment.setContext(CONTEXT);
        comment.setWrittenDate(WRITTEN_DATE);

        Comment sameComment = new Comment(ID, USER_ID, REQUEST, CONTEXT, WRITTEN_DATE);
        Assert.assertEquals(comment, sameComment);
    }

    @Test
    public void to_string_test(){
        Comment comment = new Comment();
        comment.setId(ID);
        comment.setUserId(USER_ID);
        comment.setRequest(REQUEST);
        comment.setContext(CONTEXT);
        comment.setWrittenDate(WRITTEN_DATE);

        String string = comment.toString();
        Assert.assertEquals("Comment(id=1, userId=COMMENT_USER_ID01, request=Request(id=1, category=Category(id=1, name=CATEGORY_NAME01), userId=REQUEST_USER_ID01, intro=REQUEST_INTRO_01, context=REQUEST_CONTEXT_01, available=false, writtenDate=-999999999-01-01T00:00, view=0), context=COMMENT_CONTEXT01, writtenDate=-999999999-01-01T00:00)", string);
    }
}
