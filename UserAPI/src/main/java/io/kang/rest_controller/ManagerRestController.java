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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/UserAPI/auth/manager")
public class ManagerRestController {
    @Autowired
    private AccountService accountService;

    @GetMapping("user_list")
    public List<PrincipalVO> fetchUserList(){
        return accountService.fetchUserList();
    }

    @GetMapping("user_info/{loginId}")
    public ResponseEntity<?> fetchAnyUserInfo(@PathVariable String loginId){
        DetailDTO detailDTO = accountService.fetchDetailInfo(loginId);
        if(detailDTO == null) return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        else if(detailDTO.getUser() != null){
            UserDTO userDTO = detailDTO.getUser();
            if(userDTO.getUserType() == Type.USER || userDTO.getUserType() == Type.MANAGER)
                return ResponseEntity.ok(detailDTO);
            else
                return new ResponseEntity<Void>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        return new ResponseEntity<Void>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @PutMapping("manager_up/{loginId}")
    public ResponseEntity<?> managerUp(@PathVariable String loginId){
        accountService.executeManagerLevelUp(loginId);
        return ResponseEntity.ok(String.format("아이디가 %s인 회원의 등급이 매니저로 바뀌었습니다. 착오가 있으시면 관리자에게 연락하여 조치하시길 바랍니다.", loginId));
    }
}
