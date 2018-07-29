package io.kang.rest_controller;

import io.kang.dto.DetailDTO;
import io.kang.dto.UserDTO;
import io.kang.enumeration.Type;
import io.kang.service.integrate_service.interfaces.AccountService;
import io.kang.vo.PrincipalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/UserAPI/auth/admin")
public class AdminRestController {
    @Autowired
    private AccountService accountService;

    @GetMapping("user_list")
    public List<PrincipalVO> fetchUserList(){
        return accountService.fetchUserList();
    }

    @GetMapping("user_info/{loginId}")
    public ResponseEntity<?> fetchAnyUserInfo(@PathVariable String loginId){
        DetailDTO detailDTO = accountService.fetchDetailInfo(loginId);
        if(detailDTO == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        else return ResponseEntity.ok(detailDTO);
    }

    @PutMapping("type_change/{loginId}/{typeSequence}")
    public ResponseEntity<?> excuteTypeChange(@PathVariable String loginId, @PathVariable int typeSequence){
        Type type = null;
        switch(typeSequence){
            case 0 :
                type = Type.ADMIN;
                break;
            case 1 :
                type = Type.MANAGER;
                break;
            default :
                type = Type.USER;
                break;
        }
        UserDTO userDTO = accountService.executeAdminLevelChange(loginId, type);
        if(userDTO == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        else return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("force_fired/{loginId}")
    public ResponseEntity<?> forceFired(@PathVariable String loginId){
        accountService.executeForceFired(loginId);
        return ResponseEntity.ok(String.format("아이디가 %s인 회원을 강퇴 시켰습니다. 이 회원의 복구는 영원히 불가능합니다.", loginId));
    }
}
