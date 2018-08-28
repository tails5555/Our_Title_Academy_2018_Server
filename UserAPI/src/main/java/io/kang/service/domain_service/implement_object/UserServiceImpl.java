package io.kang.service.domain_service.implement_object;

import io.kang.domain.User;
import io.kang.dto.UserDTO;
import io.kang.enumeration.Type;
import io.kang.repository.UserRepository;
import io.kang.service.domain_service.interfaces.UserService;
import io.kang.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream().map(user -> UserDTO.builtToDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByUserType(final Type type) {
        return userRepository.findByUserType(type)
                .stream().map(user -> UserDTO.builtToDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByNicknameContains(final String nickname) {
        return userRepository.findByNicknameContains(nickname)
                .stream().map(user -> UserDTO.builtToDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getOne(final Long id) {
        if(userRepository.existsById(id))
            return UserDTO.builtToDTO(userRepository.getOne(id));
        else return null;
    }

    @Override
    public UserDTO findById(final Long id) {
        Optional<User> tmpUser = userRepository.findById(id);
        if(tmpUser.isPresent())
            return UserDTO.builtToDTO(tmpUser.get());
        else return null;
    }

    @Override
    public UserDTO findByLoginId(final String loginId) {
        Optional<User> tmpUser = userRepository.findByLoginId(loginId);
        if(tmpUser.isPresent())
            return UserDTO.builtToDTO(tmpUser.get());
        else return null;
    }

    @Override
    public UserDTO create(final UserDTO userDTO) {
        User tmpUser = UserDTO.builtToDomain(userDTO);
        UserDTO createUser = UserDTO.builtToDTO(userRepository.save(tmpUser));
        if(createUser.getId() != null) return createUser;
        else return null;
    }

    @Override
    public UserDTO update(final UserDTO userDTO) {
        User tmpUser = UserDTO.builtToDomain(userDTO);
        UserDTO updateUser = UserDTO.builtToDTO(userRepository.save(tmpUser));
        return updateUser;
    }

    @Override
    public void deleteById(final Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteByLoginId(final String loginId) {
        userRepository.deleteByLoginId(loginId);
    }

    @Override
    public boolean existsById(final Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}
