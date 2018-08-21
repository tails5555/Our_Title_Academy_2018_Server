package io.kang.service.integrate_service.implement_object;

import io.kang.dto.mysql.CategoryDTO;
import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.RequestEmpathyDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.dto.redis.TodayRequestDTO;
import io.kang.enumeration.Status;
import io.kang.model.AgreeModel;
import io.kang.model.PaginationModel;
import io.kang.model.RequestModel;
import io.kang.service.domain_service.interfaces.CategoryService;
import io.kang.service.domain_service.interfaces.CommentService;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.domain_service.interfaces.TitleService;
import io.kang.service.domain_service.interfaces.TodayRequestService;
import io.kang.service.integrate_service.interfaces.RequestFetchService;
import io.kang.vo.BestTitleVO;
import io.kang.vo.BriefFetchRequestVO;
import io.kang.vo.MainFetchRequestVO;
import io.kang.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RequestFetchServiceImpl implements RequestFetchService {
    @Autowired
    private TodayRequestService todayRequestService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private EmpathyService<RequestEmpathyDTO, RequestDTO> requestEmpathyService;

    @Autowired
    private EmpathyService<TitleEmpathyDTO, TitleDTO> titleEmpathyService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private CategoryService categoryService;

    private boolean isTodayRequest(Long requestId) throws IOException {
        TodayRequestDTO todayRequestDTO = todayRequestService.findByLast();
        if(todayRequestDTO == null) return false;
        else {
            return todayRequestDTO.getRequestId() == requestId;
        }
    }

    @Override
    public List<BriefFetchRequestVO> fetchHomeBriefFetchRequests(){
        return requestService.findTop10ByCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc().stream()
                .map((requestDTO) -> {
                    TitleDTO titleDTO = titleService.findTopByRequestIdOrderByLikeCountDesc(requestDTO.getId());
                    if(titleDTO != null)
                        return BriefFetchRequestVO.builtToVO(requestDTO, titleDTO.getContext(), commentService.countByRequest(requestDTO), titleService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    else{
                        TitleDTO randomDTO = titleService.getRandomTitle(requestDTO);
                        return BriefFetchRequestVO.builtToVO(requestDTO, (randomDTO != null) ? randomDTO.getContext() : "여러분이 제목을 올려주세요.", commentService.countByRequest(requestDTO), titleService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<BriefFetchRequestVO> fetchPhotoAgreeBriefRequests(){
        return Stream.concat(requestService.findByCategoryIsNotNullAndAvailableIsFalseOrderByWrittenDateDesc().stream(), requestService.findByCategoryIsNullAndAvailableIsFalseOrderByWrittenDateDesc().stream())
                .map((requestDTO) -> {
                    TitleDTO titleDTO = titleService.findTopByRequestIdOrderByLikeCountDesc(requestDTO.getId());
                    if(titleDTO != null)
                        return BriefFetchRequestVO.builtToVO(requestDTO, titleDTO.getContext(), commentService.countByRequest(requestDTO), titleService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    else {
                        TitleDTO randomDTO = titleService.getRandomTitle(requestDTO);
                        return BriefFetchRequestVO.builtToVO(requestDTO, (randomDTO != null) ? randomDTO.getContext() : "여러분이 제목을 올려주세요.", commentService.countByRequest(requestDTO), titleService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public List<BriefFetchRequestVO> fetchAllValidRequest() {
        return requestService.findByCategoryIsNotNullAndAvailableIsTrue().stream()
                .map(requestDTO -> BriefFetchRequestVO.builtToVO(
                        requestDTO, (requestDTO != null) ? requestDTO.getContext() : "여러분이 제목을 올려주세요.", commentService.countByRequest(requestDTO),
                        titleService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE)
                    )
                )
                .collect(Collectors.toList());
    }

    @Override
    public MainFetchRequestVO fetchViewMainFetchRequestVO(final Long requestId, final String userId) {
        RequestDTO requestDTO = requestService.findById(requestId);
        return MainFetchRequestVO.builtToVO(requestDTO,
                titleService.findTop5ByRequestIdOrderByLikeCountDesc(requestId).stream()
                        .map(titleDTO -> BestTitleVO.builtToVO(titleDTO, titleEmpathyService.countByContextAndStatus(titleDTO, Status.LIKE)))
                        .collect(Collectors.toList())
                , requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE), requestEmpathyService.countByContextAndStatus(requestDTO, Status.HATE)
                , !(userId.equals("ANONYMOUS_USER")) ? requestEmpathyService.existsByUserIdAndContextAndStatus(userId, requestDTO, Status.LIKE) : null
                , !(userId.equals("ANONYMOUS_USER")) ? requestEmpathyService.existsByUserIdAndContextAndStatus(userId, requestDTO, Status.HATE) : null);
    }

    @Override
    public PaginationVO fetchCategoryBriefFetchRequests(final Long categoryId, final PaginationModel paginationModel) {
        paginationModel.setId(categoryId);
        List<BriefFetchRequestVO> briefFetchRequestVOList = requestService.findAll(paginationModel).stream()
                .map((requestDTO) -> {
                    TitleDTO titleDTO = titleService.findTopByRequestIdOrderByLikeCountDesc(requestDTO.getId());
                    if(titleDTO != null)
                        return BriefFetchRequestVO.builtToVO(requestDTO, titleDTO.getContext(), commentService.countByRequest(requestDTO), titleService.countByRequest(requestDTO),  requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    else {
                        TitleDTO randomDTO = titleService.getRandomTitle(requestDTO);
                        return BriefFetchRequestVO.builtToVO(requestDTO, (randomDTO != null) ? randomDTO.getContext() : "여러분이 제목을 올려주세요.", commentService.countByRequest(requestDTO), titleService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    }
                })
                .collect(Collectors.toList());
        return PaginationVO.builtToVO(paginationModel, briefFetchRequestVOList);
    }

    @Override
    @Transactional
    public RequestDTO executeSaveRequest(final RequestModel requestModel) {
        RequestDTO tmpRequestDTO;
        if(requestModel.getRequestId() != 0L){
            RequestDTO requestDTO = requestService.findById(requestModel.getRequestId());
            RequestDTO updateRequestDTO = RequestModel.builtToDTOIsExisted(requestDTO, requestDTO.getCategory(), requestModel);
            tmpRequestDTO = requestService.update(updateRequestDTO);
        } else {
            tmpRequestDTO = requestService.create(RequestModel.builtToDTO(requestModel));
        }
        return tmpRequestDTO;
    }

    @Override
    @Transactional
    public RequestDTO executeRequestAgree(final AgreeModel agreeModel) {
        RequestDTO requestDTO = requestService.findById(agreeModel.getRequestId());
        if(requestDTO != null){
            CategoryDTO categoryDTO = categoryService.findById(agreeModel.getCategoryId());
            requestDTO.setView(0);
            requestDTO.setAvailable(agreeModel.getAvailable());
            requestDTO.setCategory(categoryDTO);
            return requestService.update(requestDTO);
        }
        else return null;
    }

    @Override
    @Transactional
    public RequestDTO executeRequestBlocking(final Long requestId) throws IOException {
        RequestDTO requestDTO = requestService.findById(requestId);
        if(requestDTO != null && !this.isTodayRequest(requestId)){
            requestDTO.setAvailable(false);
            return requestService.update(requestDTO);
        }
        else return null;
    }

    @Override
    @Transactional
    public boolean executeDeleteRequest(final Long requestId) throws IOException {
        if(requestService.existsById(requestId) && !this.isTodayRequest(requestId)){
            requestService.deleteById(requestId);
            return true;
        } else return false;
    }

    @Override
    @Transactional
    public boolean executeRequestDeletePartition(final long[] requestIds) throws IOException {
        for(long requestId : requestIds){
            if(requestService.existsById(requestId) && !this.isTodayRequest(requestId)){
                requestService.deleteById(requestId);
            } else return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void viewPlus(final Long requestId) {
        RequestDTO requestDTO = requestService.findById(requestId);
        if(requestDTO != null){
            int view = requestDTO.getView();
            requestDTO.setView(view + 1);
            requestService.update(requestDTO);
        }
    }
}
