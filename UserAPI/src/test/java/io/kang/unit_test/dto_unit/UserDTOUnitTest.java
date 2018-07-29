package io.kang.unit_test.dto_unit;

import io.kang.domain.User;
import io.kang.dto.UserDTO;
import io.kang.unit_test.singleton_object.domain_unit.UserUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.UserDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class UserDTOUnitTest {
    private static User user = UserUpdateSingleton.INSTANCE.getInstance();
    private static UserDTO userDTO = UserDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test() throws IOException {
        UserDTO userDTO = UserDTO.builtToDTO(this.user);
        Assert.assertEquals(userDTO, this.userDTO);
    }

    @Test
    public void built_to_domain_test() throws IOException {
        User user = UserDTO.builtToDomain(this.userDTO);
        Assert.assertEquals(user, this.user);
    }
}
