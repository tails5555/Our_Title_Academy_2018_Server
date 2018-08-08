package io.kang.service.integrate_service.implement_object;

import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.enumeration.Status;
import io.kang.model.TitleModel;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.domain_service.interfaces.TitleService;
import io.kang.service.integrate_service.interfaces.TitleFetchService;
import io.kang.vo.MainTitleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TitleFetchServiceImpl implements TitleFetchService {
    @Autowired
    private TitleService titleService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private EmpathyService<TitleEmpathyDTO, TitleDTO> titleEmpathyService;

    @Override
    public MainTitleVO fetchUserRequestTitle(final Long requestId, final String userId) {
        RequestDTO requestDTO = requestService.findById(requestId);
        if(requestDTO != null) {
            TitleDTO titleDTO = titleService.findByUserIdAndRequest(userId, requestDTO);
            if(titleDTO != null)
                return MainTitleVO.builtToVO(titleDTO, titleEmpathyService.countByContextAndStatus(titleDTO, Status.LIKE), titleEmpathyService.countByContextAndStatus(titleDTO, Status.HATE), !(userId.equals("ANONYMOUS_USER")) ? titleEmpathyService.existsByUserIdAndContextAndStatus(userId, titleDTO, Status.LIKE) : null, !(userId.equals("ANONYMOUS_USER")) ?  titleEmpathyService.existsByUserIdAndContextAndStatus(userId, titleDTO, Status.HATE) : null);
            else
                return null;
        }
        else return null;
    }

    @Override
    public List<MainTitleVO> fetchMainTitleList(final Long requestId, final String userId) {
        RequestDTO requestDTO = requestService.findById(requestId);
        if(requestDTO != null)
            return titleService.findByRequestOrderByWrittenDateDesc(requestDTO).stream()
                    .map(titleDTO -> MainTitleVO.builtToVO(titleDTO, titleEmpathyService.countByContextAndStatus(titleDTO, Status.LIKE), titleEmpathyService.countByContextAndStatus(titleDTO, Status.HATE), !(userId.equals("ANONYMOUS_USER")) ? titleEmpathyService.existsByUserIdAndContextAndStatus(userId, titleDTO, Status.LIKE) : null, !(userId.equals("ANONYMOUS_USER")) ? titleEmpathyService.existsByUserIdAndContextAndStatus(userId, titleDTO, Status.HATE) : null))
                    .collect(Collectors.toList());
        else return null;
    }

    @Override
    @Transactional
    public boolean executeTitleSaving(final TitleModel titleModel) {
        RequestDTO requestDTO = requestService.findById(titleModel.getRequestId());
        if(requestDTO != null){
            TitleDTO titleDTO = titleService.findByUserIdAndRequest(titleModel.getUserId(), requestDTO);
            if(titleDTO != null){
                TitleDTO updateTitleDTO = TitleModel.builtToDTOIsExisted(titleDTO.getId(), requestDTO, titleModel, LocalDateTime.now());
                titleService.update(updateTitleDTO);
            } else {
                TitleDTO insertTitleDTO = TitleModel.builtToDTO(requestDTO, titleModel);
                titleService.create(insertTitleDTO);
            }
            return true;
        } else return false;
    }

    @Override
    @Transactional
    public boolean executeTitleDeleting(final Long titleId) {
        if(titleService.existsById(titleId)){
            titleService.deleteById(titleId);
            return true;
        } else return false;
    }
}