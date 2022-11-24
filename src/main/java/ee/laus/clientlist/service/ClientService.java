package ee.laus.clientlist.service;

import ee.laus.clientlist.dto.ClientDto;
import ee.laus.clientlist.exception.NotFoundException;
import ee.laus.clientlist.exception.PermissionException;
import ee.laus.clientlist.model.Client;
import ee.laus.clientlist.model.User;
import ee.laus.clientlist.repository.ClientRepository;
import ee.laus.clientlist.response.client.ClientListItemResponse;
import ee.laus.clientlist.response.client.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;
    private final UserService userService;
    private final ModelMapper mapper;

    public List<ClientListItemResponse> findAll() {
        return repository.findAllByUser(userService.getCurrentUser())
                .stream()
                .map(client -> mapper.map(client, ClientListItemResponse.class))
                .toList();
    }

    public ClientResponse findById(Long id) {
        final Optional<Client> client = repository.findById(id);
        if (client.isEmpty()) {
            throw new NotFoundException();
        } else if (isNotCreatedByCurrentUser(id)) {
            throw new PermissionException();
        }
        return mapper.map(client.get(), ClientResponse.class);
    }

    public ClientResponse create(ClientDto dto) {
        final User currentUser = userService.getCurrentUser();
        Client client = mapper.map(dto, Client.class);
        client.setUser(currentUser);
        client = repository.save(client);
        return mapper.map(client, ClientResponse.class);
    }

    public ClientResponse update(Long id, ClientDto dto) {
        Client client = mapper.map(dto, Client.class);
        if (isNotCreatedByCurrentUser(id)) {
            throw new PermissionException();
        }
        return mapper.map(repository.save(client), ClientResponse.class);
    }

    public boolean isNotCreatedByCurrentUser(Long id) {
        Optional<Client> client = repository.findById(id);
        if (client.isEmpty()) {
            throw new NotFoundException();
        }
        return !Objects.equals(userService.getCurrentUser().getId(), client.get().getUser().getId());
    }
}
