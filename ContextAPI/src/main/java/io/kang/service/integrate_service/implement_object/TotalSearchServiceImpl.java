package io.kang.service.integrate_service.implement_object;

import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.RequestEmpathyDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.enumeration.Status;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.domain_service.interfaces.TitleService;
import io.kang.service.integrate_service.interfaces.TotalSearchService;
import io.kang.vo.SearchResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TotalSearchServiceImpl implements TotalSearchService {
    @Autowired
    private RequestService requestService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private EmpathyService<RequestEmpathyDTO, RequestDTO> requestEmpathyService;

    @Autowired
    private EmpathyService<TitleEmpathyDTO, TitleDTO> titleEmpathyService;

    @Override
    public List<SearchResultVO> fetchSearchResultList(final String keyword){
        List<RequestDTO> requestDTOs = requestService.findByIntroContainsOrContextContains(keyword);
        List<TitleDTO> titleDTOs = titleService.findByContextContains(keyword);

        List<SearchResultVO> requestResult = requestDTOs.stream()
                .map(requestDTO -> SearchResultVO.builtToVOWithRequestDTO(requestDTO, requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE), requestEmpathyService.countByContextAndStatus(requestDTO, Status.HATE)))
                .filter(out -> out != null)
                .collect(Collectors.toList());

        List<SearchResultVO> titleResult = titleDTOs.stream()
                .map(titleDTO -> SearchResultVO.builtToVOWithTitleDTO(titleDTO, titleEmpathyService.countByContextAndStatus(titleDTO, Status.LIKE), titleEmpathyService.countByContextAndStatus(titleDTO, Status.HATE)))
                .filter(out -> out != null)
                .collect(Collectors.toList());

        return Stream.concat(requestResult.stream(), titleResult.stream()).collect(Collectors.toList());
    }
}
