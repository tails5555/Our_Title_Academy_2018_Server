package io.kang.rest_controller;

import io.kang.model.CommentModel;
import io.kang.service.integrate_service.interfaces.CommentFetchService;
import io.kang.vo.MainCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ContextAPI")
public class CommentRestController {
    @Autowired
    private CommentFetchService commentFetchService;

    @GetMapping("comments/{requestId}/{userId}")
    public ResponseEntity<List<MainCommentVO>> fetchRequestComments(@PathVariable Long requestId, @PathVariable String userId){
        return ResponseEntity.ok(commentFetchService.fetchMainCommentList(requestId, userId));
    }

    @PostMapping("comments")
    public ResponseEntity<Boolean> executeSavingComment(@RequestBody CommentModel commentModel){
        return ResponseEntity.ok(commentFetchService.executeCommentSaving(commentModel));
    }

    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<Boolean> executeDeleteComment(@PathVariable Long commentId){
        return ResponseEntity.ok(commentFetchService.executeCommentDeleting(commentId));
    }
}
