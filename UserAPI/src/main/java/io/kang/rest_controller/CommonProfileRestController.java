package io.kang.rest_controller;

import io.kang.service.integrate_service.interfaces.ProfileFetchService;
import io.kang.vo.ProfileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import java.io.IOException;
import java.security.Principal;

@RestController
@CrossOrigin
public class CommonProfileRestController {
    @Autowired
    private ProfileFetchService profileFetchService;

    private HttpHeaders generateImageHeader(ProfileVO profileVO){
        HttpHeaders header = new HttpHeaders();
        switch(profileVO.getFileSuffix()) {
            case JPG :
            case JPEG :
                header.setContentType(MediaType.IMAGE_JPEG);
                break;
            case PNG :
                header.setContentType(MediaType.IMAGE_PNG);
                break;
            case GIF :
                header.setContentType(MediaType.IMAGE_GIF);
                break;
        }
        return header;
    }

    @GetMapping("/UserAPI/auth/common/profile/my_fetch")
    public ResponseEntity<?> fetchMyProfile(Principal principal){
        ProfileVO profileVO = profileFetchService.fetchByCurrentPrincipal(principal);
        if(profileVO != null){
            return new ResponseEntity<ProfileVO>(profileVO, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value="/UserAPI/auth/common/profile/upload", consumes="multipart/form-data")
    public ResponseEntity<?> profileUpload(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {
        try {
            profileFetchService.profileUpload(file, principal);
        } catch (IOException e) {
            return new ResponseEntity<String>("프로필 설정 중에 오류가 발생했습니다.", HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<String>("프로필 설정이 완료 되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping(value="/UserAPI/auth/common/profile/release")
    public ResponseEntity<?> profileRelease(Principal principal){
        profileFetchService.executeProfileRemove(principal);
        return new ResponseEntity<String>("현재 사용자의 프로필 사진이 제거되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/UserAPI/auth/resource/profile/another_fetch/{loginId}")
    public ResponseEntity<?> fetchAnotherProfile(@PathVariable String loginId){
        ProfileVO profileVO = profileFetchService.fetchByUserLoginId(loginId);
        if(profileVO != null){
            return new ResponseEntity<ProfileVO>(profileVO, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/UserAPI/auth/resource/profile/image_profile/{loginId}")
    public ResponseEntity<?> imageProfileView(@PathVariable String loginId){
        ProfileVO profileVO = profileFetchService.fetchByUserLoginId(loginId);
        if(profileVO != null){
            HttpHeaders headers = this.generateImageHeader(profileVO);
            return new ResponseEntity<byte[]>(profileVO.getFileBytes(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
