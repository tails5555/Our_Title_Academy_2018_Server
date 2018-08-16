package io.kang.service.integrate_service.implement_object;

import io.kang.dto.mysql.RequestDTO;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.domain_service.interfaces.TodayRequestService;
import io.kang.service.integrate_service.interfaces.BattleService;
import io.kang.vo.BattleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class BattleServiceImpl implements BattleService {
    @Autowired
    private RequestService requestService;

    @Autowired
    private TodayRequestService todayRequestService;

    @Scheduled(cron = "0 0 1 * * *")
    private void setTodayBattleRequest(){
        if(todayRequestService.count() == 30L){
            todayRequestService.deleteAll();
        }

        Random random = new Random();
        List<RequestDTO> requestDTOs = requestService.findByCategoryIsNotNullAndAvaliableIsTrue();
        BattleVO battleVO;

        while(true){
            RequestDTO tmpDTO = requestDTOs.get(random.nextInt(requestDTOs.size()));
            if(!todayRequestService.existsByRequestId(tmpDTO.getId())){
                battleVO = BattleVO.builtToVO(tmpDTO.getId(), LocalDateTime.now());
                break;
            } else requestDTOs.remove(tmpDTO);
        }
        if(battleVO != null)
            todayRequestService.create(BattleVO.builtToCreateDTO(battleVO));
    }
}
