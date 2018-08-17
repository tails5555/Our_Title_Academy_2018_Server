package io.kang.service.integrate_service.listener_interfaces;

import io.kang.dto.mysql.TitleDTO;

import java.util.List;

public interface TitleCacheListener {
    void onCreateInTodayRequest(final Long titleId);
    void onUpdateInTodayRequest(final Long titleId);
    void onDeleteInTodayRequest(final Long titleId);
}
