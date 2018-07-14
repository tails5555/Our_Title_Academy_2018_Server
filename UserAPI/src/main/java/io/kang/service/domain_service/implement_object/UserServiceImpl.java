package io.kang.service.domain_service.implement_object;

import io.kang.domain.User;
import io.kang.enumeration.Type;
import io.kang.repository.UserRepository;
import io.kang.service.domain_service.interfaces.UserService;
import io.kang.vo.UserVO;
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
    public List<UserVO> findAll() {
        return userRepository.findAll()
                .stream().map(user -> UserVO.builtToVO(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserVO> findByUserType(final Type type) {
        return userRepository.findByUserType(type)
                .stream().map(user -> UserVO.builtToVO(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserVO> findByNicknameContains(final String nickname) {
        return userRepository.findByNicknameContains(nickname)
                .stream().map(user -> UserVO.builtToVO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserVO getOneVO(final Long id) {
        if(userRepository.existsById(id))
            return UserVO.builtToVO(userRepository.getOne(id));
        else return null;
    }

    @Override
    public UserVO findByIdVO(final Long id) {
        Optional<User> tmpUser = userRepository.findById(id);
        if(tmpUser.isPresent())
            return UserVO.builtToVO(tmpUser.get());
        else return null;
    }

    @Override
    public UserVO findByLoginId(final String loginId) {
        Optional<User> tmpUser = userRepository.findByLoginId(loginId);
        if(tmpUser.isPresent())
            return UserVO.builtToVO(tmpUser.get());
        else return null;
    }

    @Override
    public UserVO create(final UserVO userVO) {
        User tmpUser = UserVO.builtToDomain(userVO);
        UserVO createUser = UserVO.builtToVO(userRepository.save(tmpUser));
        if(createUser.getId() != null) return createUser;
        else return null;
    }

    @Override
    public UserVO update(final UserVO userVO) {
        User tmpUser = UserVO.builtToDomain(userVO);
        UserVO updateUser = UserVO.builtToVO(userRepository.save(tmpUser));
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
