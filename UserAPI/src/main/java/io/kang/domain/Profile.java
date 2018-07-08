package io.kang.domain;

import io.kang.enumeration.Suffix;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user"})
@Entity
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="userId")
    private User user;

    @Column(nullable=false)
    private String fileName;

    @Column(nullable=false)
    private Integer fileSize;

    @Basic(fetch=FetchType.LAZY)
    @Lob
    private byte[] fileBytes;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Suffix fileSuffix;

    @Column(nullable=false)
    private LocalDateTime uploadDate;
}
