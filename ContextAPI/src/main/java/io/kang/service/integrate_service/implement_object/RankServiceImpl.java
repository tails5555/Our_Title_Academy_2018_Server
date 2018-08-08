package io.kang.service.integrate_service.implement_object;

import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.RequestEmpathyDTO;
import io.kang.dto.mysql.TitleDTO;
import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.dto.redis.TodayRankDTO;
import io.kang.enumeration.Status;
import io.kang.service.domain_service.interfaces.CommentService;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.service.domain_service.interfaces.TitleService;
import io.kang.service.domain_service.interfaces.TodayRankService;
import io.kang.service.integrate_service.interfaces.RankService;
import io.kang.vo.BestTitleVO;
import io.kang.vo.MainFetchRequestVO;
import io.kang.vo.RankCalculateVO;
import io.kang.vo.RankFetchRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private EmpathyService<RequestEmpathyDTO, RequestDTO> requestEmpathyService;

    @Autowired
    private EmpathyService<TitleEmpathyDTO, TitleDTO> titleEmpathyService;

    @Autowired
    private TodayRankService todayRankService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TitleService titleService;

    @Scheduled(cron = "0 0/30 0/1 * * *")
    private void rankCalculate(){
        PriorityQueue<RankCalculateVO> priorityQueue = new PriorityQueue<>();
        List<RequestDTO> requestDTOs = requestService.findTop10ByCategoryIsNotNullAndAvailableOrderByViewDesc(true);

        for(RequestDTO requestDTO : requestDTOs){
            int views = requestDTO.getView();
            long empathies = requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE) - requestEmpathyService.countByContextAndStatus(requestDTO, Status.HATE);
            long comments = commentService.countByRequest(requestDTO);
            long titles = titleService.countByRequest(requestDTO);
            double totalScore = empathies * 0.2 + titles * 0.2 + comments * 0.2 + views * 0.1;
            double difference = 0;

            TodayRankDTO todayRankDTO = todayRankService.findByRequestId(requestDTO.getId());
            if(todayRankDTO != null)
                difference = totalScore - todayRankDTO.getScore();

            priorityQueue.offer(RankCalculateVO.builtToVO(requestDTO.getId(), totalScore, difference));
        }

        todayRankService.deleteAll();

        long sequence = 0;
        while(!priorityQueue.isEmpty()){
            RankCalculateVO rankCalculateVO = priorityQueue.poll();
            TodayRankDTO todayRankDTO = new TodayRankDTO(null, ++sequence, rankCalculateVO.getRequestId(), rankCalculateVO.getScore(), rankCalculateVO.getDifference());
            todayRankService.create(todayRankDTO);
        }
    }


    @Override
    public List<RankFetchRequestVO> fetchCurrentRanking() {
        List<TodayRankDTO> currentRanking = todayRankService.findAll().stream()
                .sorted(Comparator.comparing(TodayRankDTO::getSequence))
                .collect(Collectors.toList());

        double sum = currentRanking.stream().map(todayRankDTO -> todayRankDTO.getDifference()).reduce(0.0, (val1, val2) -> val1 + val2);

        return currentRanking.stream().map(
                todayRankDTO -> {
                    RequestDTO requestDTO = requestService.findById(todayRankDTO.getRequestId());
                    todayRankDTO.setDifference(Math.round(todayRankDTO.getDifference() - (sum / currentRanking.size()) * 10) * 1.0);
                    TitleDTO titleDTO = titleService.findTopByRequestIdOrderByLikeCountDesc(requestDTO.getId());
                    if(titleDTO != null)
                        return RankFetchRequestVO.builtToVO(requestDTO, todayRankDTO, titleDTO, requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE), commentService.countByRequest(requestDTO));
                    else{
                        TitleDTO randomTitleDTO = titleService.getRandomTitle(requestDTO);
                        return RankFetchRequestVO.builtToVO(requestDTO, todayRankDTO, randomTitleDTO, requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE), commentService.countByRequest(requestDTO));
                    }
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<MainFetchRequestVO> fetchMainCurrentRanking(){
        List<TodayRankDTO> currentRanking = todayRankService.findAll().stream()
                .sorted(Comparator.comparing(TodayRankDTO::getSequence))
                .collect(Collectors.toList());

        return currentRanking.stream()
                .map(todayRankDTO -> {
                    RequestDTO requestDTO = requestService.findById(todayRankDTO.getRequestId());
                    if(requestDTO != null) {
                        List<BestTitleVO> bestTitles = titleService.findTop5ByRequestIdOrderByLikeCountDesc(requestDTO.getId()).stream()
                                .map(titleDTO -> BestTitleVO.builtToVO(titleDTO, titleEmpathyService.countByContextAndStatus(titleDTO, Status.LIKE)))
                                .collect(Collectors.toList());
                        return MainFetchRequestVO.builtToVO(requestDTO, bestTitles, requestEmpathyService.countByContextAndStatus(requestDTO, Status.LIKE), requestEmpathyService.countByContextAndStatus(requestDTO, Status.HATE), false, false);
                    } else return null;
                }).collect(Collectors.toList());
    }
}
