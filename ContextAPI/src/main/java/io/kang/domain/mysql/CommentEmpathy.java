package io.kang.domain.mysql;

import io.kang.enumeration.Status;
import io.kang.enumeration.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
    name = "commentempathy",
    uniqueConstraints = @UniqueConstraint(columnNames={"userId", "commentId"})
)
@DiscriminatorValue(Type.COMMENT)
public class CommentEmpathy extends Empathy implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;

    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comment comment;

    public CommentEmpathy(){
        super();
    }

    public CommentEmpathy(Long id, Status status, LocalDateTime checkedDate, String userId, Comment comment){
        super(id, status, checkedDate);
        this.userId = userId;
        this.comment = comment;
    }
}
