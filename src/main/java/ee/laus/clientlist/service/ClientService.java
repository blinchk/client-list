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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final static Logger logger = LoggerFactory.getLogger(ClientService.class);

    public List<ClientListItemResponse> findAllByCurrentUser() {
        final User user = userService.getCurrentUser();
        List<ClientListItemResponse> clients = repository.findAllByUser(user)
                .stream()
                .map(client -> mapper.map(client, ClientListItemResponse.class))
                .toList();
        logger.info("Found {} clients for user with username={}", clients.size(), user.getUsername());
        return clients;
    }

    public ClientResponse findById(Long id) {
        final Client client = repository.findById(id).orElseThrow(NotFoundException::new);
        final User user = userService.getCurrentUser();
        logger.info("Found client with id={} for user with username={}", client.getId(), user.getUsername());
        if (client.isNotCreatedByUser(user.getId())) {
            throw new PermissionException();
        }
        return mapper.map(client, ClientResponse.class);
    }

    public ClientResponse create(ClientDto dto) {
        final User user = userService.getCurrentUser();
        Client client = mapper.map(dto, Client.class);
        client.setUser(user);
        client = repository.save(client);
        logger.info("Client with id={} created by user with username={}", client.getId(), user.getUsername());
        return mapper.map(client, ClientResponse.class);
    }

    public ClientResponse update(Long id, ClientDto dto) {
        Client client = repository.findById(id).orElseThrow(NotFoundException::new);
        final User user = userService.getCurrentUser();
        if (client.isNotCreatedByUser(user.getId())) {
            throw new PermissionException();
        }
        logger.info("Client with id={} updated by user with username={}", client.getId(), user.getUsername());
        client = mapper.map(dto, Client.class);
        client.setId(id);
        return mapper.map(repository.save(client), ClientResponse.class);
    }
}
