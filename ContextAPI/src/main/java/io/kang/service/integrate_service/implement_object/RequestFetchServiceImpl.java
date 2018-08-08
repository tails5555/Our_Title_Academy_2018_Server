package io.kang.service.integrate_service.implement_object;

import io.kang.dto.mysql.CategoryDTO;
import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.RequestEmpathyDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.enumeration.Status;
import io.kang.model.AgreeModel;
import io.kang.model.PaginationModel;
import io.kang.model.RequestModel;
import io.kang.service.domain_service.interfaces.CategoryService;
import io.kang.service.domain_service.interfaces.CommentService;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.domain_service.interfaces.TitleService;
import io.kang.service.integrate_service.interfaces.RequestFetchService;
import io.kang.vo.BestTitleVO;
import io.kang.vo.BriefFetchRequestVO;
import io.kang.vo.MainFetchRequestVO;
import io.kang.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RequestFetchServiceImpl implements RequestFetchService {
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

    @Override
    public List<BriefFetchRequestVO> fetchHomeBriefFetchRequests(){
        return requestService.findTop10ByCategoryIsNotNullAndAvailableOrderByWrittenDateDesc(true).stream()
                .map((requestDTO) -> {
                    TitleDTO titleDTO = titleService.findTopByRequestIdOrderByLikeCountDesc(requestDTO.getId());
                    if(titleDTO != null)
                        return BriefFetchRequestVO.builtToVO(requestDTO, titleDTO.getContext(), commentService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    else{
                        TitleDTO randomDTO = titleService.getRandomTitle(requestDTO);
                        return BriefFetchRequestVO.builtToVO(requestDTO, (randomDTO != null) ? randomDTO.getContext() : "여러분이 제목을 올려주세요.", commentService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<BriefFetchRequestVO> fetchPhotoAgreeBriefRequests(){
        return Stream.concat(requestService.findByCategoryIsNotNullAndAvailableIsFalseOrderByWrittenDateDesc().stream(), requestService.findByCategoryIsNullOrderByWrittenDateDesc().stream())
                .map((requestDTO) -> {
                    TitleDTO titleDTO = titleService.findTopByRequestIdOrderByLikeCountDesc(requestDTO.getId());
                    if(titleDTO != null)
                        return BriefFetchRequestVO.builtToVO(requestDTO, titleDTO.getContext(), commentService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    else {
                        TitleDTO randomDTO = titleService.getRandomTitle(requestDTO);
                        return BriefFetchRequestVO.builtToVO(requestDTO, (randomDTO != null) ? randomDTO.getContext() : "여러분이 제목을 올려주세요.", commentService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    }
                }).collect(Collectors.toList());
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
                        return BriefFetchRequestVO.builtToVO(requestDTO, titleDTO.getContext(), commentService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    else {
                        TitleDTO randomDTO = titleService.getRandomTitle(requestDTO);
                        return BriefFetchRequestVO.builtToVO(requestDTO, (randomDTO != null) ? randomDTO.getContext() : "여러분이 제목을 올려주세요.", commentService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    }
                })
                .collect(Collectors.toList());
        return PaginationVO.builtToVO(paginationModel, briefFetchRequestVOList);
    }

    @Override
    @Transactional
    public RequestDTO executeCreateRequest(RequestModel requestModel) {
        RequestDTO requestDTO = requestService.create(RequestModel.builtToDTO(requestModel));
        return requestDTO;
    }

    @Override
    @Transactional
    public RequestDTO executeRequestAgree(AgreeModel agreeModel) {
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
    public RequestDTO executeRequestBlocking(Long requestId) {
        RequestDTO requestDTO = requestService.findById(requestId);
        if(requestDTO != null){
            requestDTO.setAvailable(false);
            requestDTO.setCategory(null);
            return requestService.update(requestDTO);
        }
        else return null;
    }

    @Override
    @Transactional
    public void viewPlus(Long requestId) {
        RequestDTO requestDTO = requestService.findById(requestId);
        if(requestDTO != null){
            int view = requestDTO.getView();
            requestDTO.setView(view + 1);
            requestService.update(requestDTO);
        }
    }
}
