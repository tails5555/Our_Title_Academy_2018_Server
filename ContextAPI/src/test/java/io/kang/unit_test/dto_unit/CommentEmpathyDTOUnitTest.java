package io.kang.unit_test.dto_unit;

import io.kang.domain.mysql.CommentEmpathy;
import io.kang.dto.mysql.CommentEmpathyDTO;
import io.kang.unit_test.testing_singleton.domain_unit.CommentEmpathyUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.CommentEmpathyDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

public class CommentEmpathyDTOUnitTest {
    private static final CommentEmpathy commentEmpathy = CommentEmpathyUpdateSingleton.INSTANCE.getInstance();
    private static final CommentEmpathyDTO commentEmpathyDTO = CommentEmpathyDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test(){
        CommentEmpathyDTO commentEmpathyDTO = CommentEmpathyDTO.builtToDTO(this.commentEmpathy);
        Assert.assertEquals(commentEmpathyDTO, this.commentEmpathyDTO);
    }

    @Test
    public void built_to_domain_test(){
        CommentEmpathy commentEmpathy = CommentEmpathyDTO.builtToDomain(this.commentEmpathyDTO);
        Assert.assertEquals(commentEmpathy, this.commentEmpathy);
    }
}
