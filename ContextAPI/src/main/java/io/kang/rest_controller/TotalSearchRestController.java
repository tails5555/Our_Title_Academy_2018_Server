package io.kang.rest_controller;

import io.kang.service.integrate_service.interfaces.TotalSearchService;
import io.kang.vo.SearchResultVO;
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
@RequestMapping("/ContextAPI/search")
public class TotalSearchRestController {
    @Autowired
    private TotalSearchService totalSearchService;

    @GetMapping("{keyword}")
    public ResponseEntity<List<SearchResultVO>> fetchTotalSearchResult(@PathVariable String keyword){
        return ResponseEntity.ok(totalSearchService.fetchSearchResultList(keyword));
    }
}
