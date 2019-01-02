package io.kang.service.integrate_service.implement_object;

import io.kang.dto.mysql.CategoryDTO;
import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.RequestEmpathyDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.enumeration.Status;
import io.kang.enumeration.Type;
import io.kang.service.domain_service.interfaces.CategoryService;
import io.kang.service.domain_service.interfaces.CommentService;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.domain_service.interfaces.TitleService;
import io.kang.service.integrate_service.interfaces.MyContextService;
import io.kang.vo.BriefFetchRequestVO;
import io.kang.vo.ContextStatisticVO;
import io.kang.vo.MainTitleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyContextServiceImpl implements MyContextService {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private EmpathyService<RequestEmpathyDTO, RequestDTO> requestEmpathyService;

    @Autowired
    private EmpathyService<TitleEmpathyDTO, TitleDTO> titleEmpathyService;

    private ContextStatisticVO createContextStatisticVO(final String userId, final CategoryDTO categoryDTO, final String type){
        List<RequestDTO> requestDTOs = null;
        List<TitleDTO> titleDTOs = null;
        if(type.equals(Type.REQUEST)) {
            requestDTOs = requestService.findByUserIdAndCategoryOrderByWrittenDateDesc(userId, categoryDTO);
            if (requestDTOs.isEmpty()) return null;
        } else if(type.equals(Type.TITLE)){
            titleDTOs = titleService.findByUserIdAndRequestCategory(userId, categoryDTO);
            if(titleDTOs.isEmpty()) return null;
        }
        long count = (requestDTOs != null) ? requestDTOs.stream().count() : titleDTOs.stream().count();
        long statusCount = (requestDTOs != null) ? requestDTOs.stream()
                .map(requestDTO -> requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE) - requestEmpathyService.countByContextAndStatus(requestDTO, Status.HATE))
                .reduce(0L, (before, after) -> before + after) : titleDTOs.stream()
                .map(titleDTO -> titleEmpathyService.countByContextAndStatus(titleDTO, Status.LIKE) - titleEmpathyService.countByContextAndStatus(titleDTO, Status.HATE))
                .reduce(0L, (before, after) -> before + after);

        return ContextStatisticVO.builtToVO(categoryDTO.getName(), count, statusCount);
    }

    @Override
    public List<BriefFetchRequestVO> fetchMyValidRequestList(final String userId){
        return requestService.findByUserIdAndCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc(userId).stream()
                .map(requestDTO -> {
                    TitleDTO titleDTO = titleService.findTopByRequestIdOrderByLikeCountDesc(requestDTO.getId());
                    if(titleDTO != null)
                        return BriefFetchRequestVO.builtToVO(requestDTO, titleDTO.getContext(), commentService.countByRequest(requestDTO), titleService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    else{
                        TitleDTO randomDTO = titleService.getRandomTitle(requestDTO);
                        return BriefFetchRequestVO.builtToVO(requestDTO, (randomDTO != null) ? randomDTO.getContext() : "여러분이 제목을 올려주세요.", commentService.countByRequest(requestDTO), titleService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public List<BriefFetchRequestVO> fetchMyNonValidRequestList(final String userId){
        return requestService.findByUserIdAndAvailableIsFalseOrderByWrittenDateDesc(userId).stream()
                .map(requestDTO -> {
                    TitleDTO titleDTO = titleService.findTopByRequestIdOrderByLikeCountDesc(requestDTO.getId());
                    if(titleDTO != null)
                        return BriefFetchRequestVO.builtToVO(requestDTO, titleDTO.getContext(), commentService.countByRequest(requestDTO), titleService.countByRequest(requestDTO),requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    else{
                        TitleDTO randomDTO = titleService.getRandomTitle(requestDTO);
                        return BriefFetchRequestVO.builtToVO(requestDTO, (randomDTO != null) ? randomDTO.getContext() : "여러분이 제목을 올려주세요.", commentService.countByRequest(requestDTO), titleService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public List<ContextStatisticVO> fetchMyStatisticRequestList(final String userId) {
        return categoryService.findAll().stream()
                .map(categoryDTO -> this.createContextStatisticVO(userId, categoryDTO, Type.REQUEST))
                .filter(out -> out != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContextStatisticVO> fetchMyStatisticTitleList(final String userId) {
        return categoryService.findAll().stream()
                .map(categoryDTO -> this.createContextStatisticVO(userId, categoryDTO, Type.TITLE))
                .filter(out -> out != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<MainTitleVO> fetchMyTitleList(final String userId){
        return titleService.findByUserIdOrderByWrittenDateDesc(userId).stream()
                .filter(titleDTO -> titleDTO.getRequest().getCategory() != null)
                .map(titleDTO -> MainTitleVO.builtToVO(titleDTO, titleEmpathyService.countByContextAndStatus(titleDTO, Status.LIKE), titleEmpathyService.countByContextAndStatus(titleDTO, Status.HATE), !(userId.equals("ANONYMOUS_USER")) ? titleEmpathyService.existsByUserIdAndContextAndStatus(userId, titleDTO, Status.LIKE) : null, !(userId.equals("ANONYMOUS_USER")) ? titleEmpathyService.existsByUserIdAndContextAndStatus(userId, titleDTO, Status.HATE) : null))
                .collect(Collectors.toList());
    }
}
