package io.kang.service.integrate_service.implement_object;

import io.kang.dto.ProfileDTO;
import io.kang.dto.UserDTO;
import io.kang.enumeration.Suffix;
import io.kang.exception.CustomException;
import io.kang.service.domain_service.interfaces.ProfileService;
import io.kang.service.domain_service.interfaces.UserService;
import io.kang.service.integrate_service.interfaces.ProfileFetchService;
import io.kang.util.Encryption;
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

    private String fileNameEncryption(final String fileName) {
        int infix=fileName.lastIndexOf('.');
        String fileSuffix = fileName.substring(infix, fileName.length());
        String filePrefix = Encryption.encrypt(fileName.substring(0, infix-1), Encryption.SHA256);
        return filePrefix + fileSuffix;
    }

    @Override
    @Transactional
    public void executeProfileRemove(final Principal principal){
        ProfileDTO profileDTO = profileService.findByUserLoginId(principal.getName());
        if(profileDTO == null) return;
        else profileService.deleteById(profileDTO.getId());
    }

    @Override
    @Transactional
    public void profileUpload(final MultipartFile file, final Principal principal) throws IOException, CustomException {
        byte[] fileBytes = file.getBytes();
        if(fileBytes == null || fileBytes.length <= 0) return;

        UserDTO userDTO = userService.findByLoginId(principal.getName());
        if(userDTO == null){
            throw new CustomException("User Is Not Existed. Try Again.", HttpStatus.NO_CONTENT);
        }

        ProfileDTO profileDTO = profileService.findByUserLoginId(principal.getName());

        try( ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes) ) {
            String fileName = file.getOriginalFilename();
            int infix = fileName.lastIndexOf('.');
            String fileSuffix = fileName.substring(infix + 1, fileName.length());
            if(profileDTO != null){
                profileDTO.setUser(userDTO);
                profileDTO.setFileName(this.fileNameEncryption(fileName));
                profileDTO.setFileSize(fileBytes.length);
                profileDTO.setFileBytes(fileBytes);
                profileDTO.setFileSuffix(Suffix.valueOf(fileSuffix.toUpperCase()));
                profileDTO.setUploadDate(LocalDateTime.now());
                profileService.update(profileDTO);
            }else{
                profileDTO = new ProfileDTO(0L, userDTO, this.fileNameEncryption(fileName), fileBytes.length, fileBytes, Suffix.valueOf(fileSuffix.toUpperCase()), LocalDateTime.now());
                profileDTO.setId(0L);
                profileService.create(profileDTO);
            }
        } catch (IOException e){
            throw new CustomException("Server IO Exception. Try Again.", HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ProfileDTO fetchByCurrentPrincipal(final Principal principal){
        ProfileDTO profileDTO = profileService.findByUserLoginId(principal.getName());
        if(profileDTO == null || profileDTO.getFileSize() <= 0) return null;
        else return profileDTO;
    }

    @Override
    public ProfileDTO fetchByUserLoginId(final String loginId){
        ProfileDTO profileDTO = profileService.findByUserLoginId(loginId);
        if(profileDTO == null || profileDTO.getFileSize() <= 0) return null;
        else return profileDTO;
    }
}
