package br.com.barber_shop_api.services.queries.impls;

import br.com.barber_shop_api.entities.ClientEntity;
import br.com.barber_shop_api.exceptions.NotFoundException;
import br.com.barber_shop_api.exceptions.PhoneInUseException;
import br.com.barber_shop_api.repositories.IClientRepository;
import br.com.barber_shop_api.services.queries.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {

    private final IClientRepository repository;

    @Override
    public ClientEntity findById(final long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Não foi encontrado o cliente de id " + id)
        );
    }

    @Override
    public List<ClientEntity> list() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(final String phone) {
        if (repository.existsByPhone(phone)) {
            throw new PhoneInUseException("O telefone " + phone + " já está em uso");
        }
    }

    @Override
    public void verifyPhone(final long id, final String phone) {
        var optional = repository.findByPhone(phone);
        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(), phone)) {
            throw new PhoneInUseException("O telefone " + phone + " já está em uso");
        }
    }

    @Override
    public void verifyEmail(final String email) {
        if (repository.existsByEmail(email)) {
            throw new PhoneInUseException("O e-mail " + email + " já está em uso");
        }
    }

    @Override
    public void verifyEmail(final long id, final String email) {
        var optional = repository.findByEmail(email);
        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(), email)) {
            throw new PhoneInUseException("O e-mail " + email + " já está em uso");
        }
    }
}
