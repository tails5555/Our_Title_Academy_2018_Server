package io.kang.rest_controller;

import io.kang.enumeration.Status;
import io.kang.service.integrate_service.interfaces.IntegrateEmpathyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/ContextAPI/empathy/")
public class EmpathyRestController {
    @Autowired
    private IntegrateEmpathyService integrateEmpathyService;

    @PostMapping("/checking/title_empathy/{titleId}/{method}/{userId}")
    public ResponseEntity<Void> executeTitleEmpathyChecking(@PathVariable Long titleId, @PathVariable String method, @PathVariable String userId){
        integrateEmpathyService.checkedTitleEmpathy(titleId, userId, Status.valueOf(method.toUpperCase()));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/checking/request_empathy/{requestId}/{method}/{userId}")
    public ResponseEntity<Void> executeRequestEmpathyChecking(@PathVariable Long requestId, @PathVariable String method, @PathVariable String userId){
        integrateEmpathyService.checkedRequestEmpathy(requestId, userId, Status.valueOf(method.toUpperCase()));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/checking/comment_empathy/{commentId}/{method}/{userId}")
    public ResponseEntity<Void> executeCommentEmpathyChecking(@PathVariable Long commentId, @PathVariable String method, @PathVariable String userId){
        integrateEmpathyService.checkedCommentEmpathy(commentId, userId, Status.valueOf(method.toUpperCase()));
        return new ResponseEntity(HttpStatus.OK);
    }
}
