package br.com.barber_shop_api.controllers;

import br.com.barber_shop_api.controllers.request.SaveClientRequest;
import br.com.barber_shop_api.controllers.request.UpdateClientRequest;
import br.com.barber_shop_api.controllers.response.ClientDetailResponse;
import br.com.barber_shop_api.controllers.response.ListClientResponse;
import br.com.barber_shop_api.controllers.response.SaveClientResponse;
import br.com.barber_shop_api.controllers.response.UpdateClientResponse;
import br.com.barber_shop_api.entities.ClientEntity;
import br.com.barber_shop_api.services.IClientService;
import br.com.barber_shop_api.services.queries.IClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final IClientService service;
    private final IClientQueryService queryService;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request) {
        var entity = new ClientEntity();
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setPhone((request.phone()));
        service.save(entity);

        return new SaveClientResponse(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone());
    }

    @PutMapping("/{id}")
    UpdateClientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdateClientRequest request) {
        var entity = queryService.findById(id); // Agora utilizando queryService corretamente
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setPhone(request.phone());
        service.update(entity);

        return new UpdateClientResponse(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    ClientDetailResponse findById(@PathVariable final long id) {
        var entity = queryService.findById(id);
        return new ClientDetailResponse(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone());
    }

    @GetMapping
    List<ListClientResponse> list() {
        var entities = queryService.list();
        return entities.stream()
                .map(entity -> new ListClientResponse(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone()))
                .toList();
    }
}
