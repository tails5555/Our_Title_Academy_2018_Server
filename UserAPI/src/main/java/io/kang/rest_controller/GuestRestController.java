package io.kang.rest_controller;

import io.kang.exception.CustomException;
import io.kang.model.FindModel;
import io.kang.model.LoginModel;
import io.kang.model.SignModel;
import io.kang.service.domain_service.interfaces.AgeService;
import io.kang.service.domain_service.interfaces.CityService;
import io.kang.service.domain_service.interfaces.UserService;
import io.kang.service.integrate_service.interfaces.AccountService;
import io.kang.service.integrate_service.interfaces.TokenLoginService;
import io.kang.vo.AgeVO;
import io.kang.vo.CityVO;
import io.kang.vo.DetailVO;
import io.kang.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/UserAPI/auth/guest")
public class GuestRestController {
    @Autowired
    private AgeService ageService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenLoginService tokenLoginService;

    @Autowired
    private AccountService accountService;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginModel loginModel){
        try {
            return ResponseEntity.ok(tokenLoginService.tokenLogin(loginModel.getLoginId(), loginModel.getPassword()));
        } catch(CustomException e){
            return new ResponseEntity(e.getMessage(), e.getHttpStatus());
        }
    }

    @PostMapping("sign_up")
    public ResponseEntity<?> signUp(@RequestBody SignModel signModel){
        DetailVO detailVO = accountService.executeSignUp(signModel);
        if(detailVO != null)
            return ResponseEntity.ok(detailVO);
        else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("id_confirm/{loginId}")
    public ResponseEntity<Boolean> idConfirm(@PathVariable String loginId){
        UserVO userVO = userService.findByLoginId(loginId);
        return (userVO == null) ? ResponseEntity.ok(true) : ResponseEntity.ok(false);
    }

    @GetMapping("ageList")
    public ResponseEntity<List<AgeVO>> ageList(){
        return ResponseEntity.ok(ageService.findAll());
    }

    @GetMapping("cityList")
    public ResponseEntity<List<CityVO>> cityList(){
        return ResponseEntity.ok(cityService.findAll());
    }

    @PostMapping("find_loginId")
    public ResponseEntity<?> findLoginId(@RequestBody FindModel findModel){
        String loginId = accountService.fetchLoginId(findModel);
        return (loginId != null) ? ResponseEntity.ok(loginId) : new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
