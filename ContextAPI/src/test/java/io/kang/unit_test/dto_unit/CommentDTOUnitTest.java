package io.kang.unit_test.dto_unit;

import io.kang.domain.mysql.Comment;
import io.kang.dto.mysql.CommentDTO;
import io.kang.unit_test.testing_singleton.domain_unit.CommentUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.CommentDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

public class CommentDTOUnitTest {
    private static final Comment comment = CommentUpdateSingleton.INSTANCE.getInstance();
    private static final CommentDTO commentDTO = CommentDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test(){
        CommentDTO commentDTO = CommentDTO.builtToDTO(this.comment);
        Assert.assertEquals(commentDTO, this.commentDTO);
    }

    @Test
    public void built_to_domain_test(){
        Comment comment = CommentDTO.builtToDomain(this.commentDTO);
        Assert.assertEquals(comment, this.comment);
    }
}
