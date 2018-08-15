package io.kang.rest_controller;

import io.kang.dto.mysql.RequestDTO;
import io.kang.model.AgreeModel;
import io.kang.model.OptionModel;
import io.kang.model.PaginationModel;
import io.kang.model.RequestModel;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.integrate_service.interfaces.PhotoFetchService;
import io.kang.service.integrate_service.interfaces.RequestFetchService;
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
@RequestMapping("/ContextAPI/request/")
public class RequestRestController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestFetchService requestFetchService;

    @Autowired
    private PhotoFetchService photoFetchService;

    @GetMapping("/fetch_option/search")
    public ResponseEntity<List<OptionModel>> fetchSearchBy(){
        return ResponseEntity.ok(requestService.getSearchByModel());
    }

    @GetMapping("/fetch_option/order")
    public ResponseEntity<List<OptionModel>> fetchOrderBy(){
        return ResponseEntity.ok(requestService.getOrderByModel());
    }

    @GetMapping("/fetch_option/size")
    public ResponseEntity<List<Integer>> fetchSizeBy(){
        return ResponseEntity.ok(requestService.getSizeByModel());
    }

    @GetMapping("/fetch_brief/home")
    public ResponseEntity<List<BriefFetchRequestVO>> fetchHomeRequest(){
        return ResponseEntity.ok(requestFetchService.fetchHomeBriefFetchRequests());
    }

    @PostMapping("/fetch_brief/category/{categoryId}")
    public ResponseEntity<PaginationVO> fetchCategoryRequest(@PathVariable Long categoryId, @RequestBody PaginationModel paginationModel){
        return ResponseEntity.ok(requestFetchService.fetchCategoryBriefFetchRequests(categoryId, paginationModel));
    }

    @GetMapping("/fetch_brief/agree_list")
    public ResponseEntity<List<BriefFetchRequestVO>> fetchAgreeRequest(){
        return ResponseEntity.ok(requestFetchService.fetchPhotoAgreeBriefRequests());
    }

    @GetMapping("/fetch_main/view/{requestId}/{userId}")
    public ResponseEntity<MainFetchRequestVO> fetchMainRequest(@PathVariable Long requestId, @PathVariable String userId){
        requestFetchService.viewPlus(requestId);
        return ResponseEntity.ok(requestFetchService.fetchViewMainFetchRequestVO(requestId, userId));
    }

    @PostMapping(value="/execute_create", consumes = {"multipart/form-data"})
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

    @PutMapping(value="/execute_update")
    public ResponseEntity<Boolean> executeUpdateRequest(@RequestBody RequestModel requestModel){
        RequestDTO requestDTO = requestFetchService.executeSaveRequest(requestModel);
        if(requestDTO != null) return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else return new ResponseEntity<Boolean>(false, HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/agree_request")
    public ResponseEntity<Boolean> executeAgreeRequest(@RequestBody AgreeModel agreeModel){
        RequestDTO requestDTO = requestFetchService.executeRequestAgree(agreeModel);
        if(requestDTO != null)
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/block_request/{requestId}")
    public ResponseEntity<Boolean> executeBlockRequest(@PathVariable Long requestId){
        RequestDTO requestDTO = requestFetchService.executeRequestBlocking(requestId);
        if(requestDTO != null)
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/delete_request/{requestId}")
    public ResponseEntity<Boolean> executeRequestDelete(@PathVariable Long requestId){
        if(requestFetchService.executeDeleteRequest(requestId))
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else return new ResponseEntity<Boolean>(false, HttpStatus.OK);
    }
}
