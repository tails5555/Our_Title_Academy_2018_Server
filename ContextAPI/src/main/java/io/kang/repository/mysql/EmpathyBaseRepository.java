package io.kang.repository.mysql;

import io.kang.domain.mysql.Empathy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EmpathyBaseRepository<T extends Empathy> extends JpaRepository<T, Long> {

}
