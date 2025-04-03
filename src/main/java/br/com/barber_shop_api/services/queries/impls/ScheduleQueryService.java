package br.com.barber_shop_api.services.queries.impls;

import br.com.barber_shop_api.entities.ScheduleEntity;
import br.com.barber_shop_api.exceptions.NotFoundException;
import br.com.barber_shop_api.exceptions.ScheduleInUseException;
import br.com.barber_shop_api.repositories.IScheduleRepository;
import br.com.barber_shop_api.services.queries.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private final IScheduleRepository repository;

    @Override
    public ScheduleEntity findById(final long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Agendamento não encontrado")
        );
    }

    @Override
    public List<ScheduleEntity> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt) {
        if (repository.existsByStartAtAndEndAt(startAt, endAt)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String startFormatted = startAt.format(formatter);
            String endFormatted = endAt.format(formatter);

            throw new ScheduleInUseException("Já existe um cliente agendado no horário solicitado: "
                    + "Início: " + startFormatted + ", Fim: " + endFormatted);
        }
    }

}
