package io.kang.rest_controller;

import io.kang.dto.AgeDTO;
import io.kang.dto.CityDTO;
import io.kang.dto.DetailDTO;
import io.kang.exception.CustomException;
import io.kang.model.SignModel;
import io.kang.service.domain_service.interfaces.AgeService;
import io.kang.service.domain_service.interfaces.CityService;
import io.kang.service.integrate_service.interfaces.AccountService;
import io.kang.vo.AccessVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/UserAPI/auth/common")
public class CommonRestController {
    @Autowired
    private AgeService ageService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AccountService accountService;

    @GetMapping("denied")
    public ResponseEntity<String> denied(){
        return new ResponseEntity<String>("Authorization Roles Denied 403 Error", HttpStatus.FORBIDDEN);
    }

    @GetMapping("current_access")
    public ResponseEntity<?> currentAccess(HttpServletRequest request, Principal principal){
        try {
            return new ResponseEntity<AccessVO>(accountService.fetchCurrentAccess(principal, request), HttpStatus.OK);
        } catch(CustomException e){
            return new ResponseEntity(e.getMessage(), e.getHttpStatus());
        }
    }

    @GetMapping("sign_info")
    public ResponseEntity<?> fetchSignInfo(Principal principal){
        SignModel signModel = accountService.fetchSignInfo(principal);
        if(signModel != null)
            return ResponseEntity.ok(signModel);
        else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("sign_update")
    public ResponseEntity<?> signUpdate(@RequestBody SignModel signModel){
        DetailDTO detailDTO = accountService.executeSignInfoUpdate(signModel);
        if(detailDTO != null)
            return ResponseEntity.ok(detailDTO);
        else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("volunteer_fired")
    public ResponseEntity<String> volunteerFired(HttpServletRequest request, HttpServletResponse response, Principal principal){
        accountService.executeVolunteerFired(principal);
        logout(request, response);
        return new ResponseEntity<>("회원 탈퇴 처리가 완료 되었습니다. 그 동안 제목 학원을 이용해 주셔서 감사합니다.", HttpStatus.OK);
    }

    @GetMapping("ageList")
    public ResponseEntity<List<AgeDTO>> ageList(){
        return ResponseEntity.ok(ageService.findAll());
    }

    @GetMapping("cityList")
    public ResponseEntity<List<CityDTO>> cityList(){
        return ResponseEntity.ok(cityService.findAll());
    }

    @PostMapping("confirm_password")
    public ResponseEntity<Boolean> confirmPassword(Principal principal, @RequestBody String inputPassword){
        return ResponseEntity.ok(accountService.confirmCurrentPassword(principal, inputPassword.replace("=", "")));
    }
}
