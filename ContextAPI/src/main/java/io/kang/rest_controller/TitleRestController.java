package io.kang.rest_controller;

import io.kang.model.TitleModel;
import io.kang.service.integrate_service.interfaces.TitleFetchService;
import io.kang.vo.MainTitleVO;
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

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ContextAPI")
public class TitleRestController {
    @Autowired
    private TitleFetchService titleFetchService;

    @GetMapping("titles/main/{requestId}/{userId}")
    public ResponseEntity<List<MainTitleVO>> fetchRequestMainTitle(@PathVariable Long requestId, @PathVariable String userId){
        return ResponseEntity.ok(titleFetchService.fetchMainTitleList(requestId, userId));
    }

    @GetMapping("titles")
    public ResponseEntity<List<MainTitleVO>> fetchAllTitles(){
        return ResponseEntity.ok(titleFetchService.fetchAllTitleList());
    }

    @GetMapping("titles/user/{requestId}/{userId}")
    public ResponseEntity<?> fetchHasRequestTitle(@PathVariable Long requestId, @PathVariable String userId){
        if(userId.equals("ANONYMOUS_USER"))
            return ResponseEntity.ok(null);
        else return ResponseEntity.ok(titleFetchService.fetchUserRequestTitle(requestId, userId));
    }

    @PostMapping("titles")
    public ResponseEntity<Boolean> executeSavingTitle(@RequestBody TitleModel titleModel) throws IOException {
        return ResponseEntity.ok(titleFetchService.executeTitleSaving(titleModel));
    }

    @DeleteMapping("titles/{titleId}")
    public ResponseEntity<Boolean> executeDeleteTitle(@PathVariable Long titleId) throws IOException {
        return ResponseEntity.ok(titleFetchService.executeTitleDeleting(titleId));
    }

    @DeleteMapping("titles")
    public ResponseEntity<Boolean> executePartitionDelete(@RequestBody long[] titleIds) throws IOException{
        return ResponseEntity.ok(titleFetchService.executeTitlePartitionDeleting(titleIds));
    }
}
