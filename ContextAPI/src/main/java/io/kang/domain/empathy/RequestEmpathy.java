package io.kang.domain.empathy;

import io.kang.domain.Request;
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
    name = "requestempathy",
    uniqueConstraints = @UniqueConstraint(columnNames={"userId", "requestId"})
)
@DiscriminatorValue(Type.REQUEST)
public class RequestEmpathy extends Empathy implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;

    @ManyToOne
    @JoinColumn(name = "requestId")
    private Request request;

    public RequestEmpathy(){
        super();
    }

    public RequestEmpathy(Long id, Status status, LocalDateTime checkedDate, String userId, Request request){
        super(id, status, checkedDate);
        this.userId = userId;
        this.request = request;
    }
}
