package io.kang.service.integrate_service.implement_object;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.RequestEmpathyDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.dto.redis.TodayRequestDTO;
import io.kang.enumeration.Status;
import io.kang.service.domain_service.interfaces.BattleTitleService;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.domain_service.interfaces.TitleService;
import io.kang.service.domain_service.interfaces.TodayRequestService;
import io.kang.service.integrate_service.interfaces.BattleService;
import io.kang.vo.BattleFetchRequestVO;
import io.kang.vo.MainTitleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BattleServiceImpl implements BattleService {
    @Autowired
    private RequestService requestService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private EmpathyService<RequestEmpathyDTO, RequestDTO> requestEmpathyService;

    @Autowired
    private EmpathyService<TitleEmpathyDTO, TitleDTO> titleEmpathyService;

    @Autowired
    private TodayRequestService todayRequestService;

    @Autowired
    private BattleTitleService battleTitleService;

    private Set<Long> fetchRequestIdSet() {
        List<TodayRequestDTO> todayRequestDTOs = todayRequestService.findAll();
        return todayRequestDTOs.stream()
                .map(todayRequestDTO -> todayRequestDTO.getRequestId())
                .collect(Collectors.toSet());
    }

    @Scheduled(cron = "0 0/30 0/1 * * *")
    private void validateTodayTitleList() throws IOException {
        TodayRequestDTO currentRequestDTO = todayRequestService.findByLast();
        if(currentRequestDTO == null) return;
        List<Long> titleIds = battleTitleService.findAll();
        boolean isValidateUpdate = false;

        for(Long id : titleIds){
            TitleDTO titleDTO = titleService.findById(id);
            if(currentRequestDTO.getRequestId() != titleDTO.getRequest().getId()){
                battleTitleService.deleteAll();
                isValidateUpdate = true;
                break;
            }
        }

        if(isValidateUpdate){
            RequestDTO requestDTO = requestService.findById(currentRequestDTO.getRequestId());
            if(requestDTO == null) return;
            List<TitleDTO> titleDTOs = titleService.findByRequestOrderByWrittenDateDesc(requestDTO);
            for(TitleDTO title : titleDTOs){
                battleTitleService.create(title.getId());
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    private void setTodayBattleRequest() throws JsonProcessingException {
        battleTitleService.deleteAll();

        if (todayRequestService.count() == 30L) {
            todayRequestService.deleteByFirst();
        }

        Random random = new Random();
        Set<Long> requestIds = this.fetchRequestIdSet();
        List<RequestDTO> requestDTOs = requestService.findByCategoryIsNotNullAndAvailableIsTrue();
        List<TitleDTO> titleDTOs;
        TodayRequestDTO todayRequestDTO;

        while (true) {
            RequestDTO tmpDTO = requestDTOs.get(random.nextInt(requestDTOs.size()));
            if (!requestIds.contains(tmpDTO.getId())) {
                todayRequestDTO = TodayRequestDTO.builtToDTOProperty(tmpDTO.getId(), LocalDateTime.now());
                titleDTOs = titleService.findByRequestOrderByWrittenDateDesc(tmpDTO);
                break;
            } else requestDTOs.remove(tmpDTO);
        }

        if (todayRequestDTO != null) {
            todayRequestService.create(todayRequestDTO);
            for(TitleDTO titleDTO : titleDTOs){
                battleTitleService.create(titleDTO.getId());
            }
        }
    }

    @Override
    public BattleFetchRequestVO fetchCurrentTodayRequest(final String userId) throws IOException {
        TodayRequestDTO todayRequestDTO = todayRequestService.findByLast();
        if(todayRequestDTO == null) return null;
        else {
            RequestDTO requestDTO = requestService.findById(todayRequestDTO.getRequestId());
            return BattleFetchRequestVO.builtToVO(requestDTO, requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE), requestEmpathyService.countByContextAndStatus(requestDTO, Status.HATE)
                    , !(userId.equals("ANONYMOUS_USER")) ? requestEmpathyService.existsByUserIdAndContextAndStatus(userId, requestDTO, Status.LIKE) : null
                    , !(userId.equals("ANONYMOUS_USER")) ? requestEmpathyService.existsByUserIdAndContextAndStatus(userId, requestDTO, Status.HATE) : null);
        }
    }

    @Override
    public List<MainTitleVO> fetchCurrentTodayTitle(final String userId) throws IOException {
        TodayRequestDTO todayRequestDTO = todayRequestService.findByLast();
        if(todayRequestDTO == null) return null;

        return battleTitleService.findAll().stream()
                .map(titleId -> {
                    TitleDTO titleDTO = titleService.findById(titleId);
                    return MainTitleVO.builtToVO(titleDTO, titleEmpathyService.countByContextAndStatus(titleDTO, Status.LIKE), titleEmpathyService.countByContextAndStatus(titleDTO, Status.HATE)
                            , !(userId.equals("ANONYMOUS_USER")) ? titleEmpathyService.existsByUserIdAndContextAndStatus(userId, titleDTO, Status.LIKE) : null
                            , !(userId.equals("ANONYMOUS_USER")) ? titleEmpathyService.existsByUserIdAndContextAndStatus(userId, titleDTO, Status.HATE) : null);
                })
                .collect(Collectors.toList());
    }

    @Override
    public TitleDTO fetchUserHasTodayRequestTitle(final String userId) throws IOException {
        TodayRequestDTO currentRequestDTO = todayRequestService.findByLast();
        if(currentRequestDTO == null || userId.equals("ANONYMOUS_USER")) return null;
        RequestDTO requestDTO = requestService.findById(currentRequestDTO.getRequestId());
        if(requestDTO != null) {
            return titleService.findByUserIdAndRequest(userId, requestDTO);
        }
        else return null;
    }
}
