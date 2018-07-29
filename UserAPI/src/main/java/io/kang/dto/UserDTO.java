package io.kang.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.kang.domain.User;
import io.kang.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO {
    private Long id;
    private String loginId;
    private String nickname;
    @JsonIgnore
    private String password;
    private Type userType;

    public static UserDTO builtToDTO(User user){
        UserDTO userDTO = new UserDTO(user.getId(), user.getLoginId(), user.getNickname(), user.getPassword(), user.getUserType());
        return userDTO;
    }

    public static User builtToDomain(UserDTO userDTO){
        User user = new User(userDTO.getId(), userDTO.getLoginId(), userDTO.getNickname(), userDTO.getPassword(), userDTO.getUserType());
        return user;
    }
}
