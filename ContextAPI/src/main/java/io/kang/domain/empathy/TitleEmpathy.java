package io.kang.domain.empathy;

import io.kang.domain.Title;
import io.kang.enumeration.Status;
import io.kang.enumeration.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
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
    name = "titleempathy",
    uniqueConstraints = @UniqueConstraint(columnNames={"userId", "titleId"})
)
@DiscriminatorValue(Type.TITLE)
public class TitleEmpathy extends Empathy implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;

    @ManyToOne
    @JoinColumn(name = "titleId")
    private Title title;

    public TitleEmpathy(){
        super();
    }

    public TitleEmpathy(Long id, Status status, LocalDateTime checkedDate, String userId, Title title){
        super(id, status, checkedDate);
        this.userId = userId;
        this.title = title;
    }
}
