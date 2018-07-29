package io.kang.unit_test.domain_unit;

import io.kang.domain.mysql.Comment;
import io.kang.domain.mysql.CommentEmpathy;
import io.kang.enumeration.Status;
import io.kang.unit_test.testing_singleton.domain_unit.CommentUpdateSingleton;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class CommentEmpathyUnitTest {
    private static final long ID = 1L;
    private static final Status STATUS = Status.LIKE;
    private static final LocalDateTime CHECKED_DATE = LocalDateTime.MIN;
    private static final String USER_ID = "EMPATHY_USER_ID01";
    private static final Comment COMMENT = CommentUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void id_getter_and_setter_test(){
        CommentEmpathy commentEmpathy = new CommentEmpathy();
        commentEmpathy.setId(ID);
        long id = commentEmpathy.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void status_getter_and_setter_test(){
        CommentEmpathy commentEmpathy = new CommentEmpathy();
        commentEmpathy.setStatus(STATUS);
        Status status = commentEmpathy.getStatus();
        Assert.assertEquals(status, STATUS);
    }

    @Test
    public void checked_date_getter_and_setter_test(){
        CommentEmpathy commentEmpathy = new CommentEmpathy();
        commentEmpathy.setCheckedDate(CHECKED_DATE);
        LocalDateTime checkedDate = commentEmpathy.getCheckedDate();
        Assert.assertEquals(checkedDate, CHECKED_DATE);
    }

    @Test
    public void user_id_getter_and_setter_test(){
        CommentEmpathy commentEmpathy = new CommentEmpathy();
        commentEmpathy.setUserId(USER_ID);
        String userId = commentEmpathy.getUserId();
        Assert.assertEquals(userId, USER_ID);
    }

    @Test
    public void comment_getter_and_setter_test(){
        CommentEmpathy commentEmpathy = new CommentEmpathy();
        commentEmpathy.setComment(COMMENT);
        Comment comment = commentEmpathy.getComment();
        Assert.assertEquals(comment, COMMENT);
    }

    @Test
    public void equals_test(){
        CommentEmpathy commentEmpathy = new CommentEmpathy();
        commentEmpathy.setId(ID);
        commentEmpathy.setStatus(STATUS);
        commentEmpathy.setCheckedDate(CHECKED_DATE);
        commentEmpathy.setUserId(USER_ID);
        commentEmpathy.setComment(COMMENT);

        CommentEmpathy sameCommentEmpathy = new CommentEmpathy(ID, STATUS, CHECKED_DATE, USER_ID, COMMENT);
        Assert.assertEquals(commentEmpathy, sameCommentEmpathy);
    }

    @Test
    public void to_string_test() {
        CommentEmpathy commentEmpathy = new CommentEmpathy();
        commentEmpathy.setId(ID);
        commentEmpathy.setStatus(STATUS);
        commentEmpathy.setCheckedDate(CHECKED_DATE);
        commentEmpathy.setUserId(USER_ID);
        commentEmpathy.setComment(COMMENT);

        String string = commentEmpathy.toString();
        Assert.assertEquals("CommentEmpathy(userId=EMPATHY_USER_ID01, comment=Comment(id=1, userId=COMMENT_USER_ID01, request=Request(id=1, category=Category(id=1, name=CATEGORY_NAME01), userId=REQUEST_USER_ID01, intro=REQUEST_INTRO_01, context=REQUEST_CONTEXT_01, available=false, writtenDate=-999999999-01-01T00:00, view=0), context=COMMENT_CONTEXT01, writtenDate=-999999999-01-01T00:00))", string);
    }
}
