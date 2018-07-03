package io.kang.domain;

import io.kang.enumeration.Suffix;
import io.kang.enumeration.Type;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @OneToOne
    @JoinColumn(name="userId")
    User user;

    @Column(nullable=false)
    String fileName;

    int fileSize;

    @Basic(fetch=FetchType.LAZY)
    @Lob
    byte[] fileBytes;

    @Column(nullable=false)
    @Enumerated(EnumType.ORDINAL)
    Suffix fileSuffix;

    @Column(nullable=false)
    LocalDateTime uploadDate;
}
