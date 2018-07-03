package io.kang.domain;

import io.kang.enumeration.Type;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(unique=true, nullable=false)
    String loginId;

    @Column(nullable=false)
    String nickname;

    @Column(nullable=false)
    String password;

    @Column(nullable=false)
    @Enumerated(EnumType.ORDINAL)
    Type userType;
}
