package io.kang.service.domain_service.implement_object;

import io.kang.domain.mysql.CommentEmpathy;
import io.kang.dto.mysql.CommentDTO;
import io.kang.dto.mysql.CommentEmpathyDTO;
import io.kang.enumeration.Status;
import io.kang.repository.mysql.CommentEmpathyRepository;
import io.kang.service.domain_service.interfaces.EmpathyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentEmpathyServiceImpl implements EmpathyService<CommentEmpathyDTO, CommentDTO> {
    @Autowired
    private CommentEmpathyRepository commentEmpathyRepository;

    @Override
    public List<CommentEmpathyDTO> findAll() {
        return commentEmpathyRepository.findAll().stream()
                .map(commentEmpathy -> CommentEmpathyDTO.builtToDTO(commentEmpathy))
                .collect(Collectors.toList());
    }

    @Override
    public CommentEmpathyDTO getOne(final Long id) {
        CommentEmpathy commentEmpathy = commentEmpathyRepository.getOne(id);
        if(commentEmpathy != null) return CommentEmpathyDTO.builtToDTO(commentEmpathy);
        else return null;
    }

    @Override
    public CommentEmpathyDTO findById(final Long id) {
        Optional<CommentEmpathy> tmpCommentEmpathy = commentEmpathyRepository.findById(id);
        if(tmpCommentEmpathy.isPresent()) return CommentEmpathyDTO.builtToDTO(tmpCommentEmpathy.get());
        else return null;
    }

    @Override
    public CommentEmpathyDTO findByUserIdAndContext(final String userId, final CommentDTO context) {
        Optional<CommentEmpathy> tmpCommentEmpathy = commentEmpathyRepository.findByUserIdAndComment(userId, CommentDTO.builtToDomain(context));
        if(tmpCommentEmpathy.isPresent()) return CommentEmpathyDTO.builtToDTO(tmpCommentEmpathy.get());
        else return null;
    }

    @Override
    public CommentEmpathyDTO create(final CommentEmpathyDTO baseDTO) {
        CommentEmpathy createCommentEmpathy = commentEmpathyRepository.save(CommentEmpathyDTO.builtToDomain(baseDTO));
        if(createCommentEmpathy.getId() != null) return CommentEmpathyDTO.builtToDTO(createCommentEmpathy);
        else return null;
    }

    @Override
    public CommentEmpathyDTO update(final CommentEmpathyDTO baseDTO) {
        CommentEmpathy updateCommentEmpathy = commentEmpathyRepository.save(CommentEmpathyDTO.builtToDomain(baseDTO));
        return CommentEmpathyDTO.builtToDTO(updateCommentEmpathy);
    }

    @Override
    public void deleteById(final Long id) {
        commentEmpathyRepository.deleteById(id);
    }

    @Override
    public void deleteByUserIdAndContext(final String userId, final CommentDTO context) {
        commentEmpathyRepository.deleteByUserIdAndComment(userId, CommentDTO.builtToDomain(context));
    }

    @Override
    public boolean existsById(final Long id) {
        return commentEmpathyRepository.existsById(id);
    }

    @Override
    public boolean existsByUserIdAndContext(final String userId, final CommentDTO context) {
        return commentEmpathyRepository.existsByUserIdAndComment(userId, CommentDTO.builtToDomain(context));
    }

    @Override
    public boolean existsByUserIdAndContextAndStatus(String userId, CommentDTO context, Status status) {
        return commentEmpathyRepository.existsByUserIdAndCommentAndStatus(userId, CommentDTO.builtToDomain(context), status);
    }

    @Override
    public long count() {
        return commentEmpathyRepository.count();
    }

    @Override
    public long countByContextAndStatus(final CommentDTO context, final Status status) {
        return commentEmpathyRepository.countByCommentAndStatus(CommentDTO.builtToDomain(context), status);
    }
}
