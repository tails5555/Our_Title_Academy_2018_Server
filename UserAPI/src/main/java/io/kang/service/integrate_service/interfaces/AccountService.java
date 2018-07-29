package io.kang.service.integrate_service.interfaces;

import io.kang.dto.DetailDTO;
import io.kang.dto.UserDTO;
import io.kang.enumeration.Type;
import io.kang.model.FindModel;
import io.kang.model.SignModel;
import io.kang.vo.AccessVO;
import io.kang.vo.PrincipalVO;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

public interface AccountService {
    public boolean hasAccount(final String loginId);
    public String fetchLoginId(final FindModel findModel);
    public DetailDTO executeSignUp(final SignModel signModel);

    public SignModel fetchSignInfo(final Principal principal);
    public void executeVolunteerFired(final Principal principal);
    public AccessVO fetchCurrentAccess(final Principal principal, final HttpServletRequest request);
    public DetailDTO executeSignInfoUpdate(final SignModel signModel);
    public boolean confirmCurrentPassword(final Principal principal, final String password);

    public UserDTO executeManagerLevelUp(final String loginId);
    public UserDTO executeAdminLevelChange(final String loginId, final Type type);
    public List<PrincipalVO> fetchUserList();
    public DetailDTO fetchDetailInfo(final String loginId);
    public void executeForceFired(final String loginId);
}
