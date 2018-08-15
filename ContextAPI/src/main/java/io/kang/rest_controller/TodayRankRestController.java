package io.kang.rest_controller;

import io.kang.service.integrate_service.interfaces.RankService;
import io.kang.vo.MainFetchRequestVO;
import io.kang.vo.RankFetchRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ContextAPI/rank")
public class TodayRankRestController {
    @Autowired
    private RankService rankService;

    @GetMapping("fetch_current")
    public ResponseEntity<List<RankFetchRequestVO>> fetchCurrentRank(){
        return ResponseEntity.ok(rankService.fetchCurrentRanking());
    }

    @GetMapping("fetch_main")
    public ResponseEntity<List<MainFetchRequestVO>> fetchCurrentMainRank(){
        return ResponseEntity.ok(rankService.fetchMainCurrentRanking());
    }
}
