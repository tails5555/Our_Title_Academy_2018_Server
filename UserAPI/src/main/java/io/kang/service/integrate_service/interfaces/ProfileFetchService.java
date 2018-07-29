package io.kang.service.integrate_service.interfaces;

import io.kang.dto.ProfileDTO;
import io.kang.exception.CustomException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface ProfileFetchService {
    public void executeProfileRemove(final Principal principal);
    public void profileUpload(final MultipartFile file, final Principal principal) throws IOException, CustomException;
    public ProfileDTO fetchByCurrentPrincipal(final Principal principal);
    public ProfileDTO fetchByUserLoginId(final String loginId);
}
