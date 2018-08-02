package io.kang.domain.mysql;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"titleEmpathies", "likeCount"})
@Entity
@Table(
    name = "title",
    uniqueConstraints = @UniqueConstraint(columnNames={"requestId", "userId"})
)
public class Title implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requestId")
    @NonNull
    private Request request;

    @Column(nullable = false)
    @NonNull
    private String userId;

    @Column(nullable = false)
    @NonNull
    private String context;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime writtenDate;

    @OneToMany(mappedBy = "title")
    private List<TitleEmpathy> titleEmpathies;
}
