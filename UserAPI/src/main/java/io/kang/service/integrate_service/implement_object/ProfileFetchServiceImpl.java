package io.kang.service.integrate_service.implement_object;

import io.kang.enumeration.Suffix;
import io.kang.exception.CustomException;
import io.kang.service.domain_service.interfaces.ProfileService;
import io.kang.service.domain_service.interfaces.UserService;
import io.kang.service.integrate_service.interfaces.ProfileFetchService;
import io.kang.util.Encryption;
import io.kang.vo.ProfileVO;
import io.kang.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class ProfileFetchServiceImpl implements ProfileFetchService {
    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    private String fileNameEncryption(String fileName) {
        int infix=fileName.lastIndexOf('.');
        String fileSuffix = fileName.substring(infix, fileName.length());
        String filePrefix = Encryption.encrypt(fileName.substring(0, infix-1), Encryption.SHA256);
        return filePrefix + fileSuffix;
    }

    @Override
    @Transactional
    public void executeProfileRemove(final Principal principal){
        ProfileVO profileVO = profileService.findByUserLoginId(principal.getName());
        if(profileVO == null) return;
        else profileService.deleteById(profileVO.getId());
    }

    @Override
    @Transactional
    public void profileUpload(final MultipartFile file, final Principal principal) throws IOException, CustomException {
        byte[] fileBytes = file.getBytes();
        if(fileBytes == null || fileBytes.length <= 0) return;

        UserVO userVO = userService.findByLoginId(principal.getName());
        if(userVO == null){
            throw new CustomException("User Is Not Existed. Try Again.", HttpStatus.NO_CONTENT);
        }

        ProfileVO profileVO = profileService.findByUserLoginId(principal.getName());

        try( ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes) ) {
            String fileName = file.getOriginalFilename();
            int infix = fileName.lastIndexOf('.');
            String fileSuffix = fileName.substring(infix + 1, fileName.length());
            profileVO.setFileName(this.fileNameEncryption(fileName));
            profileVO.setFileSuffix(Suffix.valueOf(fileSuffix.toUpperCase()));
            profileVO.setFileBytes(fileBytes);
            profileVO.setFileSize(fileBytes.length);
            profileVO.setUser(userVO);
            profileVO.setUploadDate(LocalDateTime.now());
            if(profileVO.getId() != null){
                profileService.update(profileVO);
            }else{
                profileVO.setId(0L);
                profileService.create(profileVO);
            }
        } catch (IOException e){
            throw new CustomException("Server IO Exception. Try Again.", HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ProfileVO fetchByCurrentPrincipal(final Principal principal){
        ProfileVO profileVO = profileService.findByUserLoginId(principal.getName());
        if(profileVO == null || profileVO.getFileSize() <= 0) return null;
        else return profileVO;
    }

    @Override
    public ProfileVO fetchByUserLoginId(final String loginId){
        ProfileVO profileVO = profileService.findByUserLoginId(loginId);
        if(profileVO == null || profileVO.getFileSize() <= 0) return null;
        else return profileVO;
    }
}
