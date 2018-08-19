package io.kang.rest_controller;

import io.kang.dto.mysql.TitleDTO;
import io.kang.enumeration.Status;
import io.kang.model.TitleModel;
import io.kang.service.integrate_service.interfaces.BattleService;
import io.kang.service.integrate_service.interfaces.IntegrateEmpathyService;
import io.kang.service.integrate_service.interfaces.TitleFetchService;
import io.kang.vo.BattleSocketVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin
public class TodayBattleMessageController {
    @Autowired
    private IntegrateEmpathyService integrateEmpathyService;

    @Autowired
    private TitleFetchService titleFetchService;

    @Autowired
    private BattleService battleService;

    @MessageMapping("/title_list/{userId}")
    @SendTo("/ota_topic/title_viewer")
    public BattleSocketVO fetchBattleTitleList(@DestinationVariable String userId) throws IOException {
        TitleDTO titleDTO = battleService.fetchUserHasTodayRequestTitle(userId);
        return BattleSocketVO
                .builtToVO(
                        titleDTO != null ? titleDTO.getId() : 0L,
                        userId,
                        battleService.fetchCurrentTodayTitle(userId),
                        userId.equals("ANONYMOUS_USER") ? null : titleDTO != null ? true : false,
                        titleDTO != null ? titleDTO.getContext() : ""
                );
    }

    @MessageMapping("/title_saving")
    @SendTo("/ota_topic/title_viewer")
    public BattleSocketVO executeSaveTitle(@RequestBody TitleModel titleModel) throws IOException {
        TitleDTO titleDTO = null;
        if(titleFetchService.executeTitleSaving(titleModel)) {
            titleDTO = battleService.fetchUserHasTodayRequestTitle(titleModel.getUserId());
        }
        return BattleSocketVO
                .builtToVO(
                    titleDTO.getId(),
                    titleDTO.getUserId(),
                    battleService.fetchCurrentTodayTitle(titleModel.getUserId()),
                    titleDTO != null ? true : false,
                    titleDTO != null ? titleDTO.getContext() : ""
                );
    }

    @MessageMapping("/title_deleting/{titleId}/{userId}")
    @SendTo("/ota_topic/title_viewer")
    public BattleSocketVO executeTitleDelete(@DestinationVariable Long titleId, @DestinationVariable String userId) throws IOException {
        titleFetchService.executeTitleDeleting(titleId);
        return BattleSocketVO
                .builtToVO(
                        0L,
                        userId,
                        battleService.fetchCurrentTodayTitle(userId),
                        false,
                        ""
                );
    }

    @MessageMapping("/title_empathy/{titleId}/{method}/{userId}")
    @SendTo("/ota_topic/title_viewer")
    public BattleSocketVO executeCheckingEmpathy(@DestinationVariable Long titleId, @DestinationVariable String method, @DestinationVariable String userId) throws IOException {
        integrateEmpathyService.checkedTitleEmpathy(titleId, userId, Status.valueOf(method.toUpperCase()));
        TitleDTO titleDTO = battleService.fetchUserHasTodayRequestTitle(userId);
        return BattleSocketVO
                .builtToVO(
                        titleDTO != null ? titleDTO.getId() : 0L,
                        userId,
                        battleService.fetchCurrentTodayTitle(userId),
                        userId.equals("ANONYMOUS_USER") ? null : titleDTO != null ? true : false,
                        titleDTO != null ? titleDTO.getContext() : ""
                );
    }
}
