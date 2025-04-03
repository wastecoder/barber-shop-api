package br.com.barber_shop_api.services;

import br.com.barber_shop_api.entities.ScheduleEntity;

public interface IScheduleService {

    ScheduleEntity save(final ScheduleEntity entity);

    void delete(final long id);

}
