package br.com.barber_shop_api.services.impls;

import br.com.barber_shop_api.entities.ScheduleEntity;
import br.com.barber_shop_api.repositories.IScheduleRepository;
import br.com.barber_shop_api.services.IScheduleService;
import br.com.barber_shop_api.services.queries.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;

    @Override
    public ScheduleEntity save(final ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());

        return repository.save(entity);
    }

    @Override
    public void delete(final long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
