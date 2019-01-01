package io.kang.rest_controller;

import io.kang.service.integrate_service.interfaces.MyContextService;
import io.kang.vo.BriefFetchRequestVO;
import io.kang.vo.ContextStatisticVO;
import io.kang.vo.MainTitleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ContextAPI/owner")
public class MyContextRestController {
    @Autowired
    private MyContextService myContextService;

    @GetMapping("request/valid/{userId}")
    public ResponseEntity<List<BriefFetchRequestVO>> fetchValidMyRequest(@PathVariable String userId){
        return ResponseEntity.ok(myContextService.fetchMyValidRequestList(userId));
    }

    @GetMapping("request/waiting/{userId}")
    public ResponseEntity<List<BriefFetchRequestVO>> fetchNonValidMyRequest(@PathVariable String userId){
        return ResponseEntity.ok(myContextService.fetchMyNonValidRequestList(userId));
    }

    @GetMapping("request/statistic/{userId}")
    public ResponseEntity<List<ContextStatisticVO>> fetchRequestStatistic(@PathVariable String userId){
        return ResponseEntity.ok(myContextService.fetchMyStatisticRequestList(userId));
    }

    @GetMapping("title/{userId}")
    public ResponseEntity<List<MainTitleVO>> fetchMyTitle(@PathVariable String userId){
        return ResponseEntity.ok(myContextService.fetchMyTitleList(userId));
    }

    @GetMapping("title/statistic/{userId}")
    public ResponseEntity<List<ContextStatisticVO>> fetchTitleStatistic(@PathVariable String userId){
        return ResponseEntity.ok(myContextService.fetchMyStatisticTitleList(userId));
    }
}
