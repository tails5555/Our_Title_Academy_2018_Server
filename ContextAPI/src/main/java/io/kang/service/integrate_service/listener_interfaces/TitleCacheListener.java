package io.kang.service.integrate_service.listener_interfaces;

public interface TitleCacheListener {
    void onCreateInTodayRequest(final Long titleId);
    void onUpdateInTodayRequest(final Long titleId);
    void onDeleteInTodayRequest(final Long titleId);
}
