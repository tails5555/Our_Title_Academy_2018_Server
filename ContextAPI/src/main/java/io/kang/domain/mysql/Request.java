package io.kang.domain.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = true)
    private Category category;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String intro;

    @Column(nullable = false)
    private String context;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    private LocalDateTime writtenDate;

    @Column(nullable = false)
    private Integer view;
}
