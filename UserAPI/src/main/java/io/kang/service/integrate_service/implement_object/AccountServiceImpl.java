package io.kang.service.integrate_service.implement_object;

import io.kang.component.JwtTokenProvider;
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
import io.kang.vo.AgeVO;
import io.kang.vo.CityVO;
import io.kang.vo.DetailVO;
import io.kang.vo.PrincipalVO;
import io.kang.vo.UserVO;
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
        DetailVO detailVO = detailService.findByNameAndEmailVO(findModel.getName(), findModel.getEmail());
        if(detailVO != null){
            UserVO userVO = detailVO.getUser();
            return userVO.getLoginId();
        } else return null;
    }

    @Override
    @Transactional
    public DetailVO executeSignUp(final SignModel signModel) {
        if (!this.validateSignUp(signModel))
            return null;

        UserVO signUserVO = SignModel.builtToUserVO(signModel);
        UserVO signAfterUserVO = userService.create(signUserVO);

        AgeVO ageVO = ageService.findByIdVO(signModel.getAgeId());
        CityVO cityVO = cityService.findByIdVO(signModel.getCityId());

        DetailVO signDetailVO = SignModel.builtToDetailVO(signModel, signAfterUserVO, ageVO, cityVO);
        return detailService.create(signDetailVO);
    }

    @Override
    public SignModel fetchSignInfo(final Principal principal) {
        String currentLoginId = principal.getName();
        if(this.hasAccount(currentLoginId)){
            DetailVO detailVO = detailService.findByLoginIdVO(currentLoginId);
            return SignModel.builtToSignModel(detailVO, detailVO.getAge(), detailVO.getCity());
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
            UserVO userVO = userService.findByLoginId(currentLoginId);
            Date loginTime = jwtTokenProvider.getIssuedAt(jwtToken);
            return AccessVO.currentAccessVO(userVO, LocalDateTime.ofInstant(loginTime.toInstant(), ZoneId.systemDefault()));
        }
    }

    @Override
    @Transactional
    public DetailVO executeSignInfoUpdate(final SignModel signModel) {
        if (!signModel.isPasswordEquals())
            return null;

        UserVO beforeUserVO = userService.findByLoginId(signModel.getLoginId());
        if(beforeUserVO == null) return null;
        UserVO afterUserVO = SignModel.builtToUserVOExisted(beforeUserVO.getId(), signModel, beforeUserVO.getUserType());
        UserVO signAfterUserVO = userService.update(afterUserVO);

        DetailVO beforeDetailVO = detailService.findByLoginIdVO(signModel.getLoginId());
        if(beforeDetailVO == null) return null;
        AgeVO ageVO = ageService.findByIdVO(signModel.getAgeId());
        CityVO cityVO = cityService.findByIdVO(signModel.getCityId());
        DetailVO signDetailVO = SignModel.builtToDetailVOExisted(beforeDetailVO.getId(), signModel, signAfterUserVO, ageVO, cityVO);
        return detailService.update(signDetailVO);
    }

    @Override
    public boolean confirmCurrentPassword(Principal principal, String password) {
        UserVO userVO = userService.findByLoginId(principal.getName());
        if(userVO == null) return false;
        else return userVO.getPassword().equals(Encryption.encrypt(password, Encryption.MD5));
    }

    @Override
    @Secured("ROLE_MANAGER")
    @Transactional
    public UserVO executeManagerLevelUp(final String loginId) {
        UserVO userVO = userService.findByLoginId(loginId);
        userVO.setUserType(Type.MANAGER);
        return userService.update(userVO);
    }

    @Override
    @Secured("ROLE_ADMIN")
    @Transactional
    public UserVO executeAdminLevelChange(final String loginId, final Type type) {
        UserVO userVO = userService.findByLoginId(loginId);
        if(userVO != null) {
            userVO.setUserType(type);
            return userService.update(userVO);
        } else return null;
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public List<PrincipalVO> fetchUserList() {
        return detailService.findAll()
                .stream().map(detailVO -> PrincipalVO.builtToVO(detailVO))
                .collect(Collectors.toList());
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public DetailVO fetchDetailInfo(final String loginId) {
        DetailVO detailVO = detailService.findByLoginIdVO(loginId);
        if(detailVO != null) {
            detailVO.getUser().setPassword(null);
            return detailVO;
        } else return null;
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @Transactional
    public void executeForceFired(final String loginId) {
        userService.deleteByLoginId(loginId);
    }
}
