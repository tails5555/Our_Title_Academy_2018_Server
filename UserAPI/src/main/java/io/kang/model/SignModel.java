package io.kang.model;

import io.kang.dto.AgeDTO;
import io.kang.dto.CityDTO;
import io.kang.dto.DetailDTO;
import io.kang.dto.UserDTO;
import io.kang.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignModel {
    private String loginId;
    private String mainPassword;
    private String subPassword;
    private String name;
    private String nickname;
    private String email;
    private String homeNumber;
    private String phoneNumber;
    private Long ageId;
    private Long cityId;
    public boolean isPasswordEquals(){
        return this.mainPassword.equals(this.subPassword);
    }

    public static UserDTO builtToUserDTO(SignModel signModel){
        return new UserDTO(null, signModel.getLoginId(), signModel.getNickname(), signModel.getMainPassword(), Type.USER);
    }

    public static UserDTO builtToUserDTOIsExisted(Long id, SignModel signModel, Type type){
        return new UserDTO(id, signModel.getLoginId(), signModel.getNickname(), signModel.getMainPassword(), type);
    }

    public static DetailDTO builtToDetailDTO(SignModel signModel, UserDTO userDTO, AgeDTO ageDTO, CityDTO cityDTO){
        return new DetailDTO(null, userDTO, signModel.getName(), signModel.getEmail(), signModel.getHomeNumber(), signModel.getPhoneNumber(), cityDTO, ageDTO);
    }

    public static DetailDTO builtToDetailDTOExisted(Long id, SignModel signModel, UserDTO userDTO, AgeDTO ageDTO, CityDTO cityDTO){
        return new DetailDTO(id, userDTO, signModel.getName(), signModel.getEmail(), signModel.getHomeNumber(), signModel.getPhoneNumber(), cityDTO, ageDTO);
    }

    public static SignModel builtToSignModel(DetailDTO detailDTO, AgeDTO ageDTO, CityDTO cityDTO){
        UserDTO userDTO = detailDTO.getUser();
        if(userDTO != null){
            return new SignModel(userDTO.getLoginId(), "", "", detailDTO.getName(), userDTO.getNickname(), detailDTO.getEmail(), detailDTO.getHomeNumber(), detailDTO.getPhoneNumber(), ageDTO.getId(), cityDTO.getId());
        } else return null;
    }
}
