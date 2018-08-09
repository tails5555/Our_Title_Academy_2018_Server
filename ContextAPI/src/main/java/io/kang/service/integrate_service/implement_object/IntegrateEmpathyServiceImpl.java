package io.kang.service.integrate_service.implement_object;

import io.kang.dto.mysql.CommentDTO;
import io.kang.dto.mysql.CommentEmpathyDTO;
import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.RequestEmpathyDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.enumeration.Status;
import io.kang.service.domain_service.interfaces.CommentService;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.domain_service.interfaces.TitleService;
import io.kang.service.integrate_service.interfaces.IntegrateEmpathyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class IntegrateEmpathyServiceImpl implements IntegrateEmpathyService {
    @Autowired
    private EmpathyService<RequestEmpathyDTO, RequestDTO> requestEmpathyService;

    @Autowired
    private EmpathyService<TitleEmpathyDTO, TitleDTO> titleEmpathyService;

    @Autowired
    private EmpathyService<CommentEmpathyDTO, CommentDTO> commentEmpathyService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private CommentService commentService;

    @Override
    @Transactional
    public void checkedTitleEmpathy(final Long titleId, final String userId, final Status status) {
        TitleDTO titleDTO = titleService.findById(titleId);
        if(titleDTO != null){
            if(!titleEmpathyService.existsByUserIdAndContext(userId, titleDTO)){
                TitleEmpathyDTO titleEmpathyDTO = new TitleEmpathyDTO(null, status, LocalDateTime.now(), userId, titleDTO);
                titleEmpathyService.create(titleEmpathyDTO);
            }else{
                TitleEmpathyDTO titleEmpathyDTO = titleEmpathyService.findByUserIdAndContext(userId, titleDTO);
                Status tmpStatus = titleEmpathyDTO.getStatus();
                if(status == tmpStatus){
                    titleEmpathyService.deleteByUserIdAndContext(userId, titleDTO);
                } else {
                    titleEmpathyDTO.setCheckedDate(LocalDateTime.now());
                    titleEmpathyDTO.setStatus(status);
                    titleEmpathyService.update(titleEmpathyDTO);
                }
            }
        }
    }

    @Override
    @Transactional
    public void checkedRequestEmpathy(final Long requestId, final String userId, final Status status) {
        RequestDTO requestDTO = requestService.findById(requestId);
        if(requestDTO != null){
            if(!requestEmpathyService.existsByUserIdAndContext(userId, requestDTO)){
                RequestEmpathyDTO requestEmpathyDTO = new RequestEmpathyDTO(null, status, LocalDateTime.now(), userId, requestDTO);
                requestEmpathyService.create(requestEmpathyDTO);
            }else{
                RequestEmpathyDTO requestEmpathyDTO = requestEmpathyService.findByUserIdAndContext(userId, requestDTO);
                Status tmpStatus = requestEmpathyDTO.getStatus();
                if(status == tmpStatus){
                    requestEmpathyService.deleteByUserIdAndContext(userId, requestDTO);
                } else {
                    requestEmpathyDTO.setCheckedDate(LocalDateTime.now());
                    requestEmpathyDTO.setStatus(status);
                    requestEmpathyService.update(requestEmpathyDTO);
                }
            }
        }
    }

    @Override
    @Transactional
    public void checkedCommentEmpathy(final Long commentId, final String userId, final Status status) {
        CommentDTO commentDTO = commentService.findById(commentId);
        if(commentDTO != null){
            if(!commentEmpathyService.existsByUserIdAndContext(userId, commentDTO)){
                CommentEmpathyDTO commentEmpathyDTO = new CommentEmpathyDTO(null, status, LocalDateTime.now(), userId, commentDTO);
                commentEmpathyService.create(commentEmpathyDTO);
            }else{
                CommentEmpathyDTO commentEmpathyDTO = commentEmpathyService.findByUserIdAndContext(userId, commentDTO);
                Status tmpStatus = commentEmpathyDTO.getStatus();
                if(status == tmpStatus){
                    commentEmpathyService.deleteByUserIdAndContext(userId, commentDTO);
                } else {
                    commentEmpathyDTO.setCheckedDate(LocalDateTime.now());
                    commentEmpathyDTO.setStatus(status);
                    commentEmpathyService.update(commentEmpathyDTO);
                }
            }
        }
    }
}
