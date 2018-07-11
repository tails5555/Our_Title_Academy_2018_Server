package io.kang.repository;

import io.kang.domain.User;
import io.kang.enumeration.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByLoginId(String loginId);
    public List<User> findByUserType(Type type);
    public List<User> findByNicknameContains(String nickname);
}
