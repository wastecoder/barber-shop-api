package br.com.barber_shop_api.controllers;


import br.com.barber_shop_api.controllers.request.SaveScheduleRequest;
import br.com.barber_shop_api.controllers.response.ClientScheduleAppointmentResponse;
import br.com.barber_shop_api.controllers.response.SaveScheduleResponse;
import br.com.barber_shop_api.controllers.response.ScheduleAppointmentMonthResponse;
import br.com.barber_shop_api.entities.ClientEntity;
import br.com.barber_shop_api.entities.ScheduleEntity;
import br.com.barber_shop_api.services.IScheduleService;
import br.com.barber_shop_api.services.queries.IScheduleQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/schedules")
@AllArgsConstructor
public class ScheduleController {

    private final IScheduleService service;
    private final IScheduleQueryService queryService;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveScheduleResponse save(@RequestBody @Valid SaveScheduleRequest request) {
        System.out.println("\n\n=============================");
        System.out.println("Recebido: " + request);

        var cliente = new ClientEntity();
        cliente.setId(request.clientId());

        var entity = new ScheduleEntity();
        entity.setClient(cliente);
        entity.setStartAt(request.startAt());
        entity.setEndAt(request.endAt());

        service.save(entity);

        return new SaveScheduleResponse(
                entity.getId(),
                entity.getStartAt(),
                entity.getEndAt(),
                entity.getClient().getId()
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final long id) {
        service.delete(id);
    }

    @GetMapping("/{year}/{month}")
    ScheduleAppointmentMonthResponse listMonth(@PathVariable final int year, @PathVariable final int month) {
        var yearMonth = YearMonth.of(year, month);
        var startAt = yearMonth.atDay(1)
                .atTime(0, 0, 0, 0)
                .atOffset(ZoneOffset.UTC);

        var endAt = yearMonth.atEndOfMonth()
                .atTime(23, 59, 59, 999_999_999)
                .atOffset(ZoneOffset.UTC);

        var entities = queryService.findInMonth(startAt, endAt);

        List<ClientScheduleAppointmentResponse> appointments = entities.stream()
                .map(entity -> new ClientScheduleAppointmentResponse(
                        entity.getId(),
                        entity.getStartAt().getDayOfMonth(),
                        entity.getStartAt(),
                        entity.getEndAt(),
                        entity.getClient().getId(),
                        entity.getClient().getName()
                ))
                .toList();

        return new ScheduleAppointmentMonthResponse(year, month, appointments);
    }
}

