package io.kang.service.domain_service.interfaces;

import io.kang.enumeration.Type;
import io.kang.vo.UserVO;

import java.util.List;

public interface UserService {
    public List<UserVO> findAll();
    public List<UserVO> findByUserType(final Type type);
    public List<UserVO> findByNicknameContains(String nickname);
    public UserVO getOneVO(final Long id);
    public UserVO findByIdVO(final Long id);
    public UserVO findByLoginId(final String loginId);
    public UserVO create(final UserVO userVO);
    public UserVO update(final UserVO userVO);
    public void deleteById(final Long id);
    public void deleteByLoginId(final String loginId);
    public boolean existsById(final Long id);
    public long count();
}
