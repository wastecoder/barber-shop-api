package br.com.barber_shop_api.services.queries;

import br.com.barber_shop_api.entities.ScheduleEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface IScheduleQueryService {

    ScheduleEntity findById(final long id);

    List<ScheduleEntity> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt);

    void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt);

}
