package io.kang.service.integrate_service.implement_object;

import io.kang.dto.mysql.CommentDTO;
import io.kang.dto.mysql.CommentEmpathyDTO;
import io.kang.dto.mysql.RequestDTO;
import io.kang.enumeration.Status;
import io.kang.model.CommentModel;
import io.kang.service.domain_service.interfaces.CommentService;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.integrate_service.interfaces.CommentFetchService;
import io.kang.vo.MainCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentFetchServiceImpl implements CommentFetchService {
    @Autowired
    private RequestService requestService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private EmpathyService<CommentEmpathyDTO, CommentDTO> commentEmpathyService;

    @Override
    public List<MainCommentVO> fetchMainCommentList(final Long requestId, final String userId) {
        RequestDTO requestDTO = requestService.findById(requestId);
        List<CommentDTO> commentList = commentService.findByRequestOrderByWrittenDateDesc(requestDTO);;
        return commentList.stream()
                .map(commentDTO -> MainCommentVO.builtToVO(commentDTO,
                        commentEmpathyService.countByContextAndStatus(commentDTO, Status.LIKE),
                        commentEmpathyService.countByContextAndStatus(commentDTO, Status.HATE),
                        !(userId.equals("ANONYMOUS_USER")) ? commentEmpathyService.existsByUserIdAndContextAndStatus(userId, commentDTO, Status.LIKE) : null,
                        !(userId.equals("ANONYMOUS_USER")) ? commentEmpathyService.existsByUserIdAndContextAndStatus(userId, commentDTO, Status.HATE) : null)
                ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean executeCommentSaving(final CommentModel commentModel) {
        RequestDTO requestDTO = requestService.findById(commentModel.getRequestId());
        if(requestDTO != null){
            CommentDTO commentDTO = commentService.findById(commentModel.getCommentId());
            if(commentDTO != null){
                CommentDTO updateCommentDTO = CommentModel.builtToDTOIsExisted(commentDTO.getId(), commentModel, requestDTO);
                commentService.update(updateCommentDTO);
            } else {
                CommentDTO insertCommentDTO = CommentModel.builtToDTO(commentModel, requestDTO);
                commentService.create(insertCommentDTO);
            }
            return true;
        } else return false;
    }

    @Override
    @Transactional
    public boolean executeCommentDeleting(final Long commentId) {
        if(commentService.existsById(commentId)){
            commentService.deleteById(commentId);
            return true;
        } else return false;
    }
}
