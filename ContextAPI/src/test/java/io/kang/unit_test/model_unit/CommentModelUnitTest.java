package io.kang.unit_test.model_unit;

import io.kang.dto.mysql.CommentDTO;
import io.kang.model.CommentModel;
import io.kang.unit_test.testing_singleton.dto_unit.CommentDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.model_unit.CommentModelSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class CommentModelUnitTest {
    @Test
    public void built_to_dto_test(){
        CommentDTO commentDTO = CommentModel.builtToDTO(CommentModelSingleton.INSTANCE.getInstance(), RequestDTOUpdateSingleton.INSTANCE.getInstance());
        commentDTO.setWrittenDate(LocalDateTime.MIN);
        Assert.assertEquals(commentDTO, CommentDTOUpdateSingleton.INSTANCE.getInstance());
    }
}
