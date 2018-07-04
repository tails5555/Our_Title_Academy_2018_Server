package io.kang.domain;

import io.kang.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true, nullable=false)
    private String loginId;

    @Column(nullable=false)
    private String nickname;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    @Enumerated(EnumType.ORDINAL)
    private Type userType;
}
