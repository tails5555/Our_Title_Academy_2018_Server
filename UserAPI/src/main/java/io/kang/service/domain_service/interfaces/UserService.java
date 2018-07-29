package io.kang.service.domain_service.interfaces;

import io.kang.dto.UserDTO;
import io.kang.enumeration.Type;

import java.util.List;

public interface UserService {
    public List<UserDTO> findAll();
    public List<UserDTO> findByUserType(final Type type);
    public List<UserDTO> findByNicknameContains(String nickname);
    public UserDTO getOne(final Long id);
    public UserDTO findById(final Long id);
    public UserDTO findByLoginId(final String loginId);
    public UserDTO create(final UserDTO userDTO);
    public UserDTO update(final UserDTO userDTO);
    public void deleteById(final Long id);
    public void deleteByLoginId(final String loginId);
    public boolean existsById(final Long id);
    public long count();
}
