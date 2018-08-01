package io.kang.service.domain_service.implement_object;

import io.kang.domain.mysql.Comment;
import io.kang.dto.mysql.CommentDTO;
import io.kang.dto.mysql.RequestDTO;
import io.kang.repository.mysql.CommentRepository;
import io.kang.service.domain_service.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentDTO> findAll() {
        return commentRepository.findAll().stream()
                .map(comment -> CommentDTO.builtToDTO(comment))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> findByUserIdOrderByWrittenDateDesc(final String userId) {
        return commentRepository.findByUserIdOrderByWrittenDateDesc(userId).stream()
                .map(comment -> CommentDTO.builtToDTO(comment))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> findByRequestOrderByWrittenDateDesc(final RequestDTO requestDTO) {
        return commentRepository.findByRequestOrderByWrittenDateDesc(RequestDTO.builtToDomain(requestDTO)).stream()
                .map(comment -> CommentDTO.builtToDTO(comment))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO getOne(final Long id) {
        Comment comment = commentRepository.getOne(id);
        if(comment != null) return CommentDTO.builtToDTO(comment);
        else return null;
    }

    @Override
    public CommentDTO findById(final Long id) {
        Optional<Comment> tmpComment = commentRepository.findById(id);
        if(tmpComment.isPresent()) return CommentDTO.builtToDTO(tmpComment.get());
        else return null;
    }

    @Override
    public CommentDTO create(final CommentDTO commentDTO) {
        Comment createComment = commentRepository.save(CommentDTO.builtToDomain(commentDTO));
        if(createComment.getId() != null) return CommentDTO.builtToDTO(createComment);
        else return null;

    }

    @Override
    public CommentDTO update(final CommentDTO commentDTO) {
        Comment updateComment = commentRepository.save(CommentDTO.builtToDomain(commentDTO));
        return CommentDTO.builtToDTO(updateComment);
    }

    @Override
    public void deleteById(final Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public boolean existsById(final Long id) {
        return commentRepository.existsById(id);
    }

    @Override
    public long count() {
        return commentRepository.count();
    }

    @Override
    public long countByRequest(RequestDTO requestDTO) {
        return commentRepository.countByRequest(RequestDTO.builtToDomain(requestDTO));
    }
}
