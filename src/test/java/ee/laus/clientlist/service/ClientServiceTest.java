package ee.laus.clientlist.service;

import ee.laus.clientlist.dto.ClientDto;
import ee.laus.clientlist.exception.NotFoundException;
import ee.laus.clientlist.exception.PermissionException;
import ee.laus.clientlist.model.Client;
import ee.laus.clientlist.model.User;
import ee.laus.clientlist.repository.ClientRepository;
import ee.laus.clientlist.response.client.ClientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Spy
    @InjectMocks
    ClientService clientService;
    @Mock
    UserService userService;
    @Mock
    ClientRepository clientRepository;
    @Mock
    ModelMapper modelMapper;

    private User user;
    private User anotherUser;
    private Client client;

    @BeforeEach
    void setUp() {
        this.user = new User();
        this.user.setId(2L);
        this.client = new Client();
        this.client.setUser(user);
        this.anotherUser = new User();
        this.anotherUser.setId(5L);
    }

    @Test
    void findAllByCurrentUser() {
        clientService.findAllByCurrentUser();
        verify(clientRepository).findAllByUser(any());
    }

    @Test
    void findById() {
        final Long id = 4L;
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));
        when(userService.getCurrentUser()).thenReturn(user);
        clientService.findById(id);
        verify(clientRepository).findById(id);
        verify(modelMapper).map(any(), eq(ClientResponse.class));
    }

    @Test
    void findById_throwsException_whenCreatedNotByCurrentUser() {
        Long id = 4L;
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));
        when(userService.getCurrentUser()).thenReturn(anotherUser);
        assertThrows(PermissionException.class, () -> clientService.findById(id));
    }

    @Test
    void findById_throwsException_whenOptionalIsEmpty() {
        final Long id = 4L;
        when(clientRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> clientService.findById(id));
    }

    @Test
    void create() {
        when(userService.getCurrentUser()).thenReturn(user);
        when(modelMapper.map(any(), eq(Client.class))).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        clientService.create(new ClientDto());
        verify(modelMapper).map(any(), eq(ClientResponse.class));
    }

    @Test
    void update() {
        final Long id = 4L;
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));
        when(userService.getCurrentUser()).thenReturn(user);
        when(modelMapper.map(any(), eq(Client.class))).thenReturn(client);
        clientService.update(id, new ClientDto());
        verify(modelMapper).map(any(), eq(ClientResponse.class));
    }

    @Test
    void update_throwsException_whenCreatedNotByCurrentUser() {
        final Long id = 4L;
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));
        when(userService.getCurrentUser()).thenReturn(anotherUser);
        assertThrows(PermissionException.class, () -> clientService.update(id, new ClientDto()));
    }

    @Test
    void update_throwsException_whenOptionalIsEmpty() {
        final Long id = 4L;
        when(clientRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> clientService.update(id, new ClientDto()));
    }
}