package io.kang.repository.mysql;

import io.kang.domain.mysql.Photo;
import io.kang.domain.mysql.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    public Optional<Photo> findByRequest(Request request);
}
