package io.kang.dto.mysql;

import io.kang.domain.mysql.RequestEmpathy;
import io.kang.enumeration.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class RequestEmpathyDTO extends EmpathyDTO{
    private String userId;
    private RequestDTO request;

    public RequestEmpathyDTO(){
        super();
    }

    public RequestEmpathyDTO(Long empathyId, Status status, LocalDateTime empathyCheckedDate, String userId, RequestDTO request){
        super(empathyId, status, empathyCheckedDate);
        this.userId = userId;
        this.request = request;
    }

    public static RequestEmpathyDTO builtToDTO(RequestEmpathy requestEmpathy){
        return new RequestEmpathyDTO(requestEmpathy.getId(), requestEmpathy.getStatus(), requestEmpathy.getCheckedDate(), requestEmpathy.getUserId(), RequestDTO.builtToDTO(requestEmpathy.getRequest()));
    }

    public static RequestEmpathy builtToDomain(RequestEmpathyDTO requestEmpathyDTO){
        return new RequestEmpathy(requestEmpathyDTO.getId(), requestEmpathyDTO.getStatus(), requestEmpathyDTO.getCheckedDate(), requestEmpathyDTO.getUserId(), RequestDTO.builtToDomain(requestEmpathyDTO.getRequest()));
    }
}
