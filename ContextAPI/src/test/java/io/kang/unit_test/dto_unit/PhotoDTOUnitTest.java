package io.kang.unit_test.dto_unit;

import io.kang.domain.mysql.Photo;
import io.kang.dto.mysql.PhotoDTO;
import io.kang.unit_test.testing_singleton.domain_unit.PhotoUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.PhotoDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

public class PhotoDTOUnitTest {
    private static final Photo photo = PhotoUpdateSingleton.INSTANCE.getInstance();
    private static final PhotoDTO photoDTO = PhotoDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test(){
        PhotoDTO photoDTO = PhotoDTO.builtToDTO(this.photo);
        Assert.assertEquals(photoDTO, this.photoDTO);
    }

    @Test
    public void built_to_domain_test(){
        Photo photo = PhotoDTO.builtToDomain(this.photoDTO);
        Assert.assertEquals(photo, this.photo);
    }
}
