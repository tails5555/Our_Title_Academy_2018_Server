package io.kang.rest_controller;

import io.kang.dto.mysql.RequestDTO;
import io.kang.model.AgreeModel;
import io.kang.model.OptionModel;
import io.kang.model.PaginationModel;
import io.kang.model.RequestModel;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.integrate_service.interfaces.BattleService;
import io.kang.service.integrate_service.interfaces.PhotoFetchService;
import io.kang.service.integrate_service.interfaces.RequestFetchService;
import io.kang.vo.BattleFetchRequestVO;
import io.kang.vo.BriefFetchRequestVO;
import io.kang.vo.MainFetchRequestVO;
import io.kang.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ContextAPI")
public class RequestRestController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestFetchService requestFetchService;

    @Autowired
    private PhotoFetchService photoFetchService;

    @Autowired
    private BattleService battleService;

    @GetMapping("requests/options/search")
    public ResponseEntity<List<OptionModel>> fetchSearchBy(){
        return ResponseEntity.ok(requestService.getSearchByModel());
    }

    @GetMapping("requests/options/order")
    public ResponseEntity<List<OptionModel>> fetchOrderBy(){
        return ResponseEntity.ok(requestService.getOrderByModel());
    }

    @GetMapping("requests/options/size")
    public ResponseEntity<List<Integer>> fetchSizeBy(){
        return ResponseEntity.ok(requestService.getSizeByModel());
    }

    @GetMapping("requests")
    public ResponseEntity<?> fetchCategoryRequest(PaginationModel paginationModel){
        return ResponseEntity.ok(requestFetchService.fetchCategoryBriefFetchRequests(paginationModel));
    }

    @GetMapping("requests/home")
    public ResponseEntity<List<BriefFetchRequestVO>> fetchHomeRequest(){
        return ResponseEntity.ok(requestFetchService.fetchHomeBriefFetchRequests());
    }

    @GetMapping("requests/agree")
    public ResponseEntity<List<BriefFetchRequestVO>> fetchAgreeRequest(){
        return ResponseEntity.ok(requestFetchService.fetchPhotoAgreeBriefRequests());
    }

    @GetMapping("requests/valid")
    public ResponseEntity<List<BriefFetchRequestVO>> fetchAllValidRequest(){
        return ResponseEntity.ok(requestFetchService.fetchAllValidRequest());
    }

    @GetMapping("requests/{requestId}/{userId}")
    public ResponseEntity<MainFetchRequestVO> fetchMainRequest(@PathVariable Long requestId, @PathVariable String userId){
        requestFetchService.viewPlus(requestId);
        return ResponseEntity.ok(requestFetchService.fetchViewMainFetchRequestVO(requestId, userId));
    }

    @GetMapping("requests/_redirect/{requestId}/{userId}")
    public ResponseEntity<MainFetchRequestVO> fetchMainRequestRedirect(@PathVariable Long requestId, @PathVariable String userId){
        return ResponseEntity.ok(requestFetchService.fetchViewMainFetchRequestVO(requestId, userId));
    }

    @GetMapping("/requests/today/{userId}")
    public ResponseEntity<?> fetchTodayBattleRequest(@PathVariable String userId) throws IOException {
        BattleFetchRequestVO battleFetchRequestVO = battleService.fetchCurrentTodayRequest(userId);
        if(battleFetchRequestVO != null) return ResponseEntity.ok(battleFetchRequestVO);
        else return ResponseEntity.noContent().build();
    }

    @PostMapping(value="requests", consumes = {"multipart/form-data"})
    public ResponseEntity<Boolean> executeCreateRequest(@RequestPart(value="requestModel") RequestModel requestModel, @RequestPart("file") MultipartFile multipartFile) throws IOException {
        RequestDTO requestDTO = requestFetchService.executeSaveRequest(requestModel);
        if(requestDTO != null) {
            try {
                photoFetchService.photoUpload(multipartFile, requestDTO.getId(), requestDTO.getUserId());
            } catch (IOException e) {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_MODIFIED);
            }
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PutMapping(value="requests")
    public ResponseEntity<Boolean> executeUpdateRequest(@RequestBody RequestModel requestModel){
        RequestDTO requestDTO = requestFetchService.executeSaveRequest(requestModel);
        if(requestDTO != null) return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else return new ResponseEntity<Boolean>(false, HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("requests/agree")
    public ResponseEntity<Boolean> executeAgreeRequest(@RequestBody AgreeModel agreeModel){
        RequestDTO requestDTO = requestFetchService.executeRequestAgree(agreeModel);
        if(requestDTO != null)
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("requests/blocking/{requestId}")
    public ResponseEntity<Boolean> executeBlockRequest(@PathVariable Long requestId) throws IOException {
        RequestDTO requestDTO = requestFetchService.executeRequestBlocking(requestId);
        if(requestDTO != null)
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("requests/{requestId}")
    public ResponseEntity<Boolean> executeRequestDelete(@PathVariable Long requestId) throws IOException {
        if(requestFetchService.executeDeleteRequest(requestId))
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else return new ResponseEntity<Boolean>(false, HttpStatus.OK);
    }

    @DeleteMapping("requests/multiple")
    public ResponseEntity<Boolean> executeRequestDeletePartition(@RequestBody long[] requestIds) throws IOException {
        return ResponseEntity.ok(requestFetchService.executeRequestDeletePartition(requestIds));
    }
}
