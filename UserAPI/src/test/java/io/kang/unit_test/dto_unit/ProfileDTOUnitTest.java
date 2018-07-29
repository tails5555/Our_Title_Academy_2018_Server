package io.kang.unit_test.dto_unit;

import io.kang.domain.Profile;
import io.kang.dto.ProfileDTO;
import io.kang.unit_test.singleton_object.domain_unit.ProfileUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.ProfileDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ProfileDTOUnitTest {
    private static Profile profile = ProfileUpdateSingleton.INSTANCE.getInstance();
    private static ProfileDTO profileDTO = ProfileDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test() throws IOException {
        ProfileDTO profileDTO = ProfileDTO.builtToDTO(this.profile);
        Assert.assertEquals(profileDTO, this.profileDTO);
    }

    @Test
    public void built_to_domain_test() throws IOException{
        Profile profile = ProfileDTO.builtToDomain(this.profileDTO);
        Assert.assertEquals(profile, this.profile);
    }
}
