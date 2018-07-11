package io.kang.model;

import io.kang.vo.PrincipalVO;
import io.kang.vo.SignVO;
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
    private String email;
    private String homeNumber;
    private String phoneNumber;
    private Long ageId;
    private Long cityId;
    public static SignModel builtToModel(SignVO signVO){
        return new SignModel(signVO.getLoginId(), signVO.getMainPassword(), signVO.getSubPassword(), signVO.getName(), signVO.getEmail(), signVO.getHomeNumber(), signVO.getPhoneNumber(), signVO.getAgeId(), signVO.getCityId());
    }
    public static SignVO builtToVO(SignModel signModel){
        return new SignVO(signModel.getLoginId(), signModel.getMainPassword(), signModel.getSubPassword(), signModel.getName(), signModel.getEmail(), signModel.getHomeNumber(), signModel.getPhoneNumber(), signModel.getAgeId(), signModel.getCityId());
    }
}
