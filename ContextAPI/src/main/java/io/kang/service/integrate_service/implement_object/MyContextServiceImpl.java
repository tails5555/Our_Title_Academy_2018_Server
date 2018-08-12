package io.kang.service.integrate_service.implement_object;

import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.RequestEmpathyDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.enumeration.Status;
import io.kang.service.domain_service.interfaces.CommentService;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.domain_service.interfaces.TitleService;
import io.kang.service.integrate_service.interfaces.MyContextService;
import io.kang.vo.BriefFetchRequestVO;
import io.kang.vo.MainTitleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyContextServiceImpl implements MyContextService {
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

    @Override
    public List<BriefFetchRequestVO> fetchMyValidRequestList(final String userId){
        return requestService.findByUserIdAndCategoryIsNotNullOrderByWrittenDateDesc(userId).stream()
                .map(requestDTO -> {
                    TitleDTO titleDTO = titleService.findTopByRequestIdOrderByLikeCountDesc(requestDTO.getId());
                    if(titleDTO != null)
                        return BriefFetchRequestVO.builtToVO(requestDTO, titleDTO.getContext(), commentService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    else{
                        TitleDTO randomDTO = titleService.getRandomTitle(requestDTO);
                        return BriefFetchRequestVO.builtToVO(requestDTO, (randomDTO != null) ? randomDTO.getContext() : "여러분이 제목을 올려주세요.", commentService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public List<BriefFetchRequestVO> fetchMyNonValidRequestList(final String userId){
        return requestService.findByUserIdAndCategoryIsNullOrderByWrittenDateDesc(userId).stream()
                .map(requestDTO -> {
                    TitleDTO titleDTO = titleService.findTopByRequestIdOrderByLikeCountDesc(requestDTO.getId());
                    if(titleDTO != null)
                        return BriefFetchRequestVO.builtToVO(requestDTO, titleDTO.getContext(), commentService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    else{
                        TitleDTO randomDTO = titleService.getRandomTitle(requestDTO);
                        return BriefFetchRequestVO.builtToVO(requestDTO, (randomDTO != null) ? randomDTO.getContext() : "여러분이 제목을 올려주세요.", commentService.countByRequest(requestDTO), requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE));
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public List<MainTitleVO> fetchMyTitleList(final String userId){
        return titleService.findByUserIdOrderByWrittenDateDesc(userId).stream()
                .map(titleDTO -> MainTitleVO.builtToVO(titleDTO, titleEmpathyService.countByContextAndStatus(titleDTO, Status.LIKE), titleEmpathyService.countByContextAndStatus(titleDTO, Status.HATE), !(userId.equals("ANONYMOUS_USER")) ? titleEmpathyService.existsByUserIdAndContextAndStatus(userId, titleDTO, Status.LIKE) : null, !(userId.equals("ANONYMOUS_USER")) ? titleEmpathyService.existsByUserIdAndContextAndStatus(userId, titleDTO, Status.HATE) : null))
                .collect(Collectors.toList());
    }
}
