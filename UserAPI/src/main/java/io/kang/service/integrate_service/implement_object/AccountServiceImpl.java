package io.kang.service.integrate_service.implement_object;

import io.kang.component.JwtTokenProvider;
import io.kang.dto.AgeDTO;
import io.kang.dto.CityDTO;
import io.kang.dto.DetailDTO;
import io.kang.dto.UserDTO;
import io.kang.enumeration.Type;
import io.kang.exception.CustomException;
import io.kang.model.FindModel;
import io.kang.model.SignModel;
import io.kang.service.domain_service.interfaces.AgeService;
import io.kang.service.domain_service.interfaces.CityService;
import io.kang.service.domain_service.interfaces.DetailService;
import io.kang.service.domain_service.interfaces.UserService;
import io.kang.service.integrate_service.interfaces.AccountService;
import io.kang.util.Encryption;
import io.kang.vo.AccessVO;
import io.kang.vo.PrincipalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private AgeService ageService;

    @Autowired
    private CityService cityService;

    private boolean validateSignUp(final SignModel signModel) {
        if (!signModel.isPasswordEquals())
            return false;
        else if (this.hasAccount(signModel.getLoginId()))
            return false;
        else return true;
    }

    @Override
    public boolean hasAccount(final String loginId) {
        return this.userService.findByLoginId(loginId) != null;
    }

    @Override
    public String fetchLoginId(final FindModel findModel) {
        DetailDTO detailDTO = detailService.findByNameAndEmail(findModel.getName(), findModel.getEmail());
        if(detailDTO != null){
            UserDTO userDTO = detailDTO.getUser();
            return userDTO.getLoginId();
        } else return null;
    }

    @Override
    @Transactional
    public DetailDTO executeSignUp(final SignModel signModel) {
        if (!this.validateSignUp(signModel))
            return null;

        UserDTO signUserDTO = SignModel.builtToUserDTO(signModel);
        signUserDTO.setPassword(Encryption.encrypt(signModel.getMainPassword(), Encryption.MD5));
        UserDTO signAfterUserDTO = userService.create(signUserDTO);

        AgeDTO ageDTO = ageService.findById(signModel.getAgeId());
        CityDTO cityDTO = cityService.findById(signModel.getCityId());

        DetailDTO signDetailDTO = SignModel.builtToDetailDTO(signModel, signAfterUserDTO, ageDTO, cityDTO);
        return detailService.create(signDetailDTO);
    }

    @Override
    public SignModel fetchSignInfo(final Principal principal) {
        String currentLoginId = principal.getName();
        if(this.hasAccount(currentLoginId)){
            DetailDTO detailDTO = detailService.findByLoginId(currentLoginId);
            return SignModel.builtToSignModel(detailDTO, detailDTO.getAge(), detailDTO.getCity());
        } else return null;
    }

    @Override
    @Transactional
    public void executeVolunteerFired(final Principal principal) {
        userService.deleteByLoginId(principal.getName());
    }

    @Override
    public AccessVO fetchCurrentAccess(final Principal principal, final HttpServletRequest request) throws CustomException {
        String jwtToken;
        String currentLoginId;
        String tokenLoginId;
        if (principal != null) {
            jwtToken = jwtTokenProvider.resolveToken(request);
            currentLoginId = principal.getName();
            tokenLoginId = jwtTokenProvider.getUsername(jwtToken);
        } else {
            return null;
        }
        if (!tokenLoginId.equals(currentLoginId))
            return null;
        else {
            UserDTO userDTO = userService.findByLoginId(currentLoginId);
            Date loginTime = jwtTokenProvider.getIssuedAt(jwtToken);
            return AccessVO.currentAccessVO(userDTO, LocalDateTime.ofInstant(loginTime.toInstant(), ZoneId.systemDefault()));
        }
    }

    @Override
    @Transactional
    public DetailDTO executeSignInfoUpdate(final SignModel signModel) {
        if (!signModel.isPasswordEquals())
            return null;

        UserDTO beforeUserDTO = userService.findByLoginId(signModel.getLoginId());
        if(beforeUserDTO == null) return null;
        UserDTO afterUserDTO = SignModel.builtToUserDTOIsExisted(beforeUserDTO.getId(), signModel, beforeUserDTO.getUserType());
        afterUserDTO.setPassword(Encryption.encrypt(signModel.getMainPassword(), Encryption.MD5));
        UserDTO signAfterUserDTO = userService.update(afterUserDTO);

        DetailDTO beforeDetailDTO = detailService.findByLoginId(signModel.getLoginId());
        if(beforeDetailDTO == null) return null;
        AgeDTO ageDTO = ageService.findById(signModel.getAgeId());
        CityDTO cityDTO = cityService.findById(signModel.getCityId());
        DetailDTO signDetailDTO = SignModel.builtToDetailDTOExisted(beforeDetailDTO.getId(), signModel, signAfterUserDTO, ageDTO, cityDTO);
        return detailService.update(signDetailDTO);
    }

    @Override
    public boolean confirmCurrentPassword(final Principal principal, final String password) {
        UserDTO userDTO = userService.findByLoginId(principal.getName());
        if(userDTO == null) return false;
        else return userDTO.getPassword().equals(Encryption.encrypt(password, Encryption.MD5));
    }

    @Override
    @Secured("ROLE_MANAGER")
    @Transactional
    public UserDTO executeManagerLevelUp(final String loginId) {
        UserDTO userDTO = userService.findByLoginId(loginId);
        userDTO.setUserType(Type.MANAGER);
        return userService.update(userDTO);
    }

    @Override
    @Secured("ROLE_ADMIN")
    @Transactional
    public UserDTO executeAdminLevelChange(final String loginId, final Type type) {
        UserDTO userDTO = userService.findByLoginId(loginId);
        if(userDTO != null) {
            userDTO.setUserType(type);
            return userService.update(userDTO);
        } else return null;
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public List<PrincipalVO> fetchUserList() {
        return detailService.findAll()
                .stream().map(detailDTO -> PrincipalVO.builtToVO(detailDTO))
                .collect(Collectors.toList());
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public DetailDTO fetchDetailInfo(final String loginId) {
        DetailDTO detailDTO = detailService.findByLoginId(loginId);
        if(detailDTO != null) {
            detailDTO.getUser().setPassword(null);
            return detailDTO;
        } else return null;
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @Transactional
    public void executeForceFired(final String loginId) {
        userService.deleteByLoginId(loginId);
    }
}
